package com.apply.service.impl;

import com.apply.entity.Gov;
import com.apply.entity.User;
import com.apply.mapper.GovMapper;
import com.apply.service.GovService;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import org.apache.ibatis.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GovServiceImpl implements GovService {

    private static String basePath = "D:\\googleDownload\\hit_stopwords.txt";

    @Autowired
    private GovMapper govMapper;
//2023-11-24T16:11:53.156049600
    @Override
    public List<Gov> getOneYearNews() {
        LocalDate now = LocalDate.now();
        return govMapper.getOneYearNews(now);
    }

    @Override
    public List<Gov> getSpecifyDateNews(LocalDate begin, LocalDate end) {

        return govMapper.getSpecifyDateNews(begin, end);
    }

    @Override
    public List<Gov> getSpecifyDateAndRegionNews(LocalDate begin, LocalDate end, String region) {
        return govMapper.getSpecifyDateAndRegionNews(begin, end, region);
    }

    @Override
    public String login(String username, String password) {
        User user = govMapper.login(username, password);
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    @Override
    public Map<String, Integer> getSpecifyDateAndRegionTop(LocalDate beginDate, LocalDate endDate, String region) {
        List<Gov> specifyDateAndRegionNews = govMapper.getSpecifyDateAndRegionNews(beginDate, endDate, region);
        StringBuilder total = new StringBuilder();
        for (Gov gov :
                specifyDateAndRegionNews) {
            total.append(gov.getTitle());
        }
        String totalString = total.toString();
        Map<String, Integer> segment = segment(totalString, null, basePath);
        System.out.println(segment);
        return segment;
    }

    public static Map<String, Integer> segment(String text, String customDictionaryPath, String stopWordsPath) {
        JiebaSegmenter segmenter = new JiebaSegmenter();

//        // 导入自定义词典
//        if (customDictionaryPath != null && !customDictionaryPath.isEmpty()) {
//            WordDictionary.getInstance().init(customDictionaryPath);
//        }

        // 导入停用词
        List<String> stopWords = FileUtil.readStopWords(stopWordsPath);

        // 分词
        List<SegToken> segTokens = segmenter.process(text, JiebaSegmenter.SegMode.INDEX);

        // 统计词频
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (SegToken segToken : segTokens) {
            String word = segToken.word.toLowerCase().trim();  // 将词语转换为小写，以避免大小写不一致
            if (!stopWords.contains(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // 按词频降序排序
        wordFrequency = sortByValueDescending(wordFrequency);

        // 获取前十个词语及其频率
        Map<String, Integer> topWords = new HashMap<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            topWords.put(entry.getKey(), entry.getValue());
            count++;
            if (count == 10) {
                break;
            }
        }

        return topWords;
    }
    class FileUtil {
        public static List<String> readStopWords(String filePath) {
            List<String> stopWords = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stopWords.add(line.trim());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stopWords;
        }
    }
    private static Map<String, Integer> sortByValueDescending(Map<String, Integer> unsortedMap) {
        return unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
