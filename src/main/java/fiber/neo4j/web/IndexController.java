package fiber.neo4j.web;

import fiber.neo4j.entity.*;
import fiber.neo4j.repository.FiberRepository;
import fiber.neo4j.repository.RoutePointRepository;
import jodd.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class IndexController {
    @Resource
    private FiberRepository fiberRepository;
    @Resource
    private RoutePointRepository routePointRepository;

    @RequestMapping("/uploadByInit")
    public void uploadByInit(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // 在这里进行对每行文本的解析操作
                // 例如，可以将每行文本存储到List<String>中或进行其他处理
                String[] s = line.split("\t");
                if (Arrays.stream(s).filter(f -> StringUtil.isEmpty(f)).collect(Collectors.toList()).size() == 0) {
                    try {
                        Long fiberId = Long.valueOf(s[0]);
                        String fiberName = s[1];
                        String nameId = s[2];
                        FacilityStage exists = FacilityStage.getFacilityStage(s[3]);
                        Long fromStationId = Long.valueOf(s[4]);
                        String fromName = s[5];
                        String fromResourceId = s[6];
                        FacilityStage fromExist = FacilityStage.getFacilityStage(s[7]);

                        Long toStationId = Long.valueOf(s[8]);
                        String toName = s[9];
                        String toResourceId = s[10];
                        FacilityStage toExist = FacilityStage.getFacilityStage(s[11]);
                        Fiber fiber = new Fiber();
                        fiber.setUuid(fiberId);
                        fiber.setName(fiberName);
                        fiber.setExists(exists);
                        fiber.setNameId(nameId);
                        fiber.setFromStationId(fromStationId);
                        fiber.setToStationId(toStationId);
                        RoutePoint from = new RoutePoint();
                        from.setUuid(fromStationId);
                        from.setName(fromName);
                        from.setResourceId(fromResourceId);
                        from.setExist(fromExist);

                        RoutePoint to = new RoutePoint();
                        to.setUuid(toStationId);
                        to.setName(toName);
                        to.setResourceId(toResourceId);
                        to.setExist(toExist);
                        fiber.setRoutePointTests(Arrays.asList(from, to));
                        fiberRepository.save(fiber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("index")
    public String index() {
        //373884,372379,1058500,331177,379644,330933
        /*RoutePoint r1 = RoutePoint.builder().name("R1").dis(10).build();
        RoutePoint r2 = RoutePoint.builder().name("R2").dis(20).build();
        RoutePoint r3 = RoutePoint.builder().name("R3").dis(30).build();
        RoutePoint r4 = RoutePoint.builder().name("R4").dis(40).build();
        RoutePoint r5 = RoutePoint.builder().name("R5").dis(50).build();
        RoutePoint r6 = RoutePoint.builder().name("R6").dis(60).build();
        List<RoutePoint> routePoints = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5, r6));
        routePointRepository.saveAll(routePoints);

        Fiber f1 = Fiber.builder().name("R1-R2").routePoints(Arrays.asList(r1, r2)).build();
        Fiber f2 = Fiber.builder().name("R2-R6").routePoints(Arrays.asList(r2, r6)).build();

        Fiber f3 = Fiber.builder().name("R1-R3").routePoints(Arrays.asList(r1, r3)).build();
        Fiber f4 = Fiber.builder().name("R3-R6").routePoints(Arrays.asList(r3, r6)).build();

        Fiber f5 = Fiber.builder().name("R1-R4").routePoints(Arrays.asList(r1, r4)).build();
        Fiber f6 = Fiber.builder().name("R4-R6").routePoints(Arrays.asList(r4, r6)).build();

        List<Fiber> fibers = new ArrayList<>(Arrays.asList(f1, f2, f3, f4, f5, f6));
        fiberRepository.saveAll(fibers);
        for (Fiber fiber : fibers) {
            for (int i = 1; i <= 12; i++) {
                FiberCore core = FiberCore.builder().coreNum(i).fiber(fiber).build();
                fiberCoreRepository.save(core);
            }
        }*/

        /*String filePath = "D:\\RoutePoint.txt";
        try {
            // 逐行读取文本文件内容并存储到List中
            List<RoutePoint> lines = Files.readAllLines(Paths.get(filePath))
                    .stream().map(it->{
                        return RoutePoint.builder().name(it).build();
                    }).collect(Collectors.toList());
            routePointRepository.saveAll(lines);
            System.out.println(lines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*String filePath = "D:\\Fiber.txt";
        try {
            // 逐行读取文本文件内容并存储到List中
            Files.readAllLines(Paths.get(filePath))
                    .stream().forEach(it -> {
                        try {
                            String[] s = it.split("\t");
                            System.out.println(s[0] + "->" + s[1] + "->" + s[2] + "->" + s[3]);
                            List<RoutePoint> routePoints = routePointRepository.findByNames(Arrays.asList(s[1], s[2]));
                            if (routePoints.size() == 2) {
                                fiberRepository.save(Fiber.builder().name(s[0]).routePoints(routePoints).dis(Double.valueOf(s[3])).build());
                            }
                        } catch (Exception e) {
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*String filePath = "D:\\RoutePoint_test.txt";
        try {
            // 逐行读取文本文件内容并存储到List中
            routePointTestRepository.saveAll(Files.readAllLines(Paths.get(filePath)).stream().map(it->{
                return RoutePointTest.builder().uuid(Long.valueOf(it)).build();
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String filePath = "D:\\Fiber_test.txt";
        try {
            // 逐行读取文本文件内容并存储到List中
            List<Fiber> fibers = Files.readAllLines(Paths.get(filePath))
                    .stream().map(it -> {
                        String[] s = it.split("\t");
                        Fiber fiber = new Fiber();
                        fiber.setUuid(Long.valueOf(s[0]));
                        fiber.setExists(FacilityStage.UNDER_RUNNING);
                        RoutePoint from = new RoutePoint();
                        from.setUuid(Long.valueOf(s[1]));
                        from.setExist(FacilityStage.UNDER_RUNNING);
                        RoutePoint to = new RoutePoint();
                        to.setUuid(Long.valueOf(s[2]));
                        to.setExist(FacilityStage.UNDER_RUNNING);
                        fiber.setRoutePointTests(Arrays.asList(from, to));
                        return fiber;
                    }).collect(Collectors.toList());
            int BATCH_SIZE = 5000;
            for (int i = 0; i < fibers.size(); i += BATCH_SIZE) {
                int endIndex = Math.min(i + BATCH_SIZE, fibers.size());
                List<Fiber> batch = fibers.subList(i, endIndex);
                // 在这里对每个批次的记录进行处理
                fiberRepository.saveAll(batch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* List<Fiber> fibers = DataGenerator.generateFibers(10000);
        List<RoutePoint> routePoints = DataGenerator.generateRoutePoints(10000);

        // 为Fiber对象随机分配RoutePoint对象的关联
        DataGenerator.assignRandomRoutePoints(fibers, routePoints);
        routePointRepository.saveAll(routePoints);
        fiberRepository.saveAll(fibers);*/
        return "success";
    }


    @RequestMapping("/fiber/getId")
    public Fiber getById(Long id) {
        Optional<Fiber> fiber = fiberRepository.findById(id);
        return fiber.orElse(null);
    }

    @RequestMapping("/fiber/deleteAll")
    public String deleteAll() {
        fiberRepository.deleteAll();
        routePointRepository.deleteAll();
        return "success";
    }

    @RequestMapping("/fiber/path")
    public Object path(String start, String end) {
       /* for (Fiber pathBetweenRoutePoint : fiberRepository.findPathBetweenRoutePoints(373884L, 330933L)) {
            System.out.println(pathBetweenRoutePoint.getName());
        }
        for (RoutePoint routePoint : routePointRepository.findByResourceId(373884L)) {
            System.out.println(routePoint.getName());
        }*/
       /* if (maps.size() > 0) {
            List<Object> objects = (List<Object>) maps.get(0).get("nodes");
            for (Object object : objects) {
                if (object instanceof RoutePoint) {
                    RoutePoint routePoint = (RoutePoint) object;
                    System.out.println(routePoint.getName());
                }
                if (object instanceof Fiber) {
                    Fiber fiber = (Fiber) object;
                    System.out.println(fiber.getName());
                }
            }
        }*/
        return fiberRepository.findShortestRoutePath();
    }
}
