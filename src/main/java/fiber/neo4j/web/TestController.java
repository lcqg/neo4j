package fiber.neo4j.web;

import fiber.neo4j.entity.Cable;
import fiber.neo4j.entity.LocNode;
import fiber.neo4j.entity.Node;
import fiber.neo4j.repository.CableRepository;
import fiber.neo4j.repository.LocNodeRepository;
import fiber.neo4j.repository.NodeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private CableRepository cableRepository;
    @Resource
    private NodeRepository nodeRepository;
    @Resource
    private LocNodeRepository locRepository;

    @RequestMapping("/index")
    public String index() {
        // 创建起点和终点节点
        Node startPoint = new Node();
        startPoint.setName("a");
        Node endPoint = new Node();
        endPoint.setName("b");
        nodeRepository.save(startPoint);
        nodeRepository.save(endPoint);

        // 创建光缆
        Cable cable = new Cable();
        cable.setName("a-b");
        cable.setStartPoint(startPoint);
        cable.setEndPoint(endPoint);
        cable.setDis(123);
        cableRepository.save(cable);
        return "success";
    }

    @RequestMapping("/index2")
    public String test2(){
        // 初始化节点
        LocNode a = new LocNode("A");
        LocNode b = new LocNode("B");
        LocNode c = new LocNode("C");
        LocNode d = new LocNode("D");
        LocNode e = new LocNode("E");
        LocNode f = new LocNode("F");

        // 初始化关系
        a.addRoadTo(b, 50);
        a.addRoadTo(c, 50);
        a.addRoadTo(d, 100);
        b.addRoadTo(d, 40);
        c.addRoadTo(d, 40);
        c.addRoadTo(e, 80);
        d.addRoadTo(e, 30);
        d.addRoadTo(f, 80);
        e.addRoadTo(f, 40);

        // 保存节点
        locRepository.save(a);
        locRepository.save(b);
        locRepository.save(c);
        locRepository.save(d);
        locRepository.save(e);
        locRepository.save(f);
        return "success";
    }
}
