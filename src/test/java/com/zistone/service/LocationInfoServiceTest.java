package com.zistone.service;

import com.zistone.gprs.StartApplication;
import com.zistone.gprs.bean.LocationInfo;
import com.zistone.gprs.service.LocationInfoService;
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
    private LocationInfoService _locationInfoService;

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
        Calendar calendar = Calendar.getInstance();
        String yearStr = String.valueOf(calendar.get(Calendar.YEAR));
        String monthStr = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String dayStr = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String startStr = yearStr + "-" + monthStr + "-" + "01" + " 00:00:00";
        String endStr = yearStr + "-" + monthStr + "-" + dayStr + " 23:59:59";
        Date startDate = SIMPLEDATEFORMAT.parse(startStr);
        Date endDate = SIMPLEDATEFORMAT.parse(endStr);
        List<LocationInfo> list = _locationInfoService.FindByDeviceIdAndTime("300234067349750", startDate, endDate);
        List<String> keyList = new ArrayList<>();
        //当天日期为键,当天所有位置为值
        List<LocationInfo> locationEverDayList = new ArrayList<>();
        for (LocationInfo locationInfo : list)
        {
            String timeStr = SIMPLEDATEFORMAT.format(locationInfo.get_createTime());
            timeStr = timeStr.split(" ")[0];
            keyList.add(timeStr);
        }
        Map<String, List<LocationInfo>> map_locationsForDay = new HashMap<>();
        for (String tempStr : keyList)
        {
            List<LocationInfo> tempList = new ArrayList<>();
            for (LocationInfo locationInfo : list)
            {
                String timeStr = SIMPLEDATEFORMAT.format(locationInfo.get_createTime());
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
                dates.add(tempLocationInfo.get_createTime());
            }
            //当天最后一条记录的新增时间
            Date lastTime = Collections.max(dates);
            for (LocationInfo tempLocationInfo : locationInfos)
            {
                if (lastTime == tempLocationInfo.get_createTime())
                {
                    locationEverDayList.add(tempLocationInfo);
                    break;
                }
            }
        }
        Collections.sort(locationEverDayList, (o1, o2) ->
        {
            //降序排列
            if (o1.get_createTime().before(o2.get_createTime()))
            {
                return 1;
            }
            if (o1.get_createTime() == o2.get_createTime())
            {
                return 0;
            }
            return -1;
        });
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
