package com.athome.mygmall.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.athome.mygmall.mapper.CourseMapper;


public class Test1 {
    private CourseMapper mapper;

//    @Before
    public void setUp() throws Exception {
        InputStream fis = null;
        InputStream inputStream = null;
        try {
            //创建Properties对象
            Properties prop = new Properties();
            //创建输入流，指向配置文件,getResourceAsStream可以从classpath加载资源
            fis= Resources.getResourceAsStream("config/jdbc.properties");
            //加载属性文件
            prop.load(fis);
            inputStream = Resources.getResourceAsStream("./mybatis/mybatis-config.xml");
            //build的第二个参数对应mybatis.xml配置文件的<environment id="development">标签的id，
            //其中后面两个参数可选，若第二个参数不写则默认为"development"
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"development",prop);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //StuScoreDao.class与配置文件StuMapper的namespace对应
            mapper = sqlSession.getMapper(CourseMapper.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

//    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    //根据学号查询学生
//    public void test1() {
//    	List<Course> selectAll = this.mapper.selectAll();
//        System.out.println("finish！");
//    }

//    @Test
//    //增加学生
//    public void test2(){
//        StuScore stuScore = new StuScore();
//        stuScore.setName("测试");
//        stuScore.setStuid(9);
//        this.mapper.insertStu(stuScore);
//    }
//
//    @Test
//    //删除
//    public void test3(){
//        this.mapper.deleteStu(9);
//    }
//
//    @Test
//    //修改数据
//    public void test4(){
//        StuScore stu = new StuScore();
//        stu.setName("测试1");
//        stu.setStuid(9);
//        this.mapper.updateStu(stu);
//    }
}