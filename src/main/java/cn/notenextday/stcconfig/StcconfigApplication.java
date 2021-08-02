package cn.notenextday.stcconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DO（ Data Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象。
 * DTO（ Data Transfer Object）：数据传输对象，Service或Manager向外传输的对象。
 * BO（ Business Object）：业务对象。 由Service层输出的封装业务逻辑的对象。
 * AO（ Application Object）：应用对象。 在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高。
 * VO（ View Object）：显示层对象，通常是Web向模板渲染引擎层传输的对象。
 * POJO（ Plain Ordinary Java Object）：在本手册中，
 * POJO专指只有setter/getter/toString的简单类，包括DO/DTO/BO/VO等。
 * Query：数据查询对象，各层接收上层的查询请求。 注意超过2个参数的查询封装，禁止使用Map类来传输。
 *
 * 自我使用
 * dao: 输入使用Query, 输出使用DO,Entity
 * service层: 输入使用AO,DTO,输出使用BO
 * web层: 输入使用DTO, 输出DTO
 *
 * 简单理解, 数据对象DO, 传输对象DTO, 展示对象VO
 * POJO=DO/DTO/BO/VO 口头称呼
 *
 */
@SpringBootApplication
public class StcconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(StcconfigApplication.class, args);
    }

}
