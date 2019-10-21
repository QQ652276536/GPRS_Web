package com.zistone.service;

import com.zistone.StartApplication;
import com.zistone.bean.LocationInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartApplication.class})
public class LocationInfoServiceTest
{
    @Autowired
    private LocationInfoService m_locationInfoService;

    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SIMPLEDATEFORMAT2 = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> List<T> getDuplicateElements(Stream<T> stream)
    {
        return stream.collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
    }

    @Test
    public void findByDeviceId() throws ParseException
    {
        List<LocationInfo> list = m_locationInfoService.FindByDeviceId("551030006334");
        List<String> keyList = new ArrayList<>();
        //当天日期为键,当天所有位置为值
        List<LocationInfo> locationEverDayList = new ArrayList<>();
        for (LocationInfo locationInfo : list)
        {
            String timeStr = SIMPLEDATEFORMAT.format(locationInfo.getM_createTime());
            timeStr = timeStr.split(" ")[0];
            keyList.add(timeStr);
        }
        Map<String, List<LocationInfo>> map_locationsForDay = new HashMap<>();
        for (String tempStr : keyList)
        {
            List<LocationInfo> tempList = new ArrayList<>();
            for (LocationInfo locationInfo : list)
            {
                String timeStr = SIMPLEDATEFORMAT.format(locationInfo.getM_createTime());
                timeStr = timeStr.split(" ")[0];
                if (tempStr.equals(timeStr))
                {
                    tempList.add(locationInfo);
                }
            }
            map_locationsForDay.put(tempStr, tempList);
        }
        for (Map.Entry<String, List<LocationInfo>> entry : map_locationsForDay.entrySet())
        {
            List<LocationInfo> locationInfos = entry.getValue();
            List<Date> dates = new ArrayList<>();
            //当天所有位置的新增时间
            for (LocationInfo tempLocationInfo : locationInfos)
            {
                dates.add(tempLocationInfo.getM_createTime());
            }
            //当天最后一条记录的新增时间
            Date lastTime = Collections.max(dates);
            for (LocationInfo tempLocationInfo : locationInfos)
            {
                if (lastTime == tempLocationInfo.getM_createTime())
                {
                    locationEverDayList.add(tempLocationInfo);
                    break;
                }
            }
        }
        for (int i = 0; i < locationEverDayList.size(); i++)
        {
            System.out.println(locationEverDayList.get(i).toString());
        }
        int a = 1;
    }

    @Test
    public void findByDeviceIdAndTime()
    {
    }

    @Test
    public void insert()
    {
    }

    @Test
    public void insertList()
    {
    }
}
