package cn.edu.ldu.servlet;

import cn.edu.ldu.bean.BmiBean;
import cn.edu.ldu.dao.DBHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jiajingong on 2017/7/4.
 */
public class BmiServlet extends HttpServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter writer;

    public BmiServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.request = request;
        this.response = response;
        this.request.setCharacterEncoding("UTF-8");
        this.response.setCharacterEncoding("UTF-8");
        //给相应内容编码
        this.response.setContentType("text/html;charset=utf-8");
        //跨域处理
        this.response.setHeader("Access-Control-Allow-Origin","*");
        //获取输出流
        writer = this.response.getWriter();
        String method = this.request.getParameter("method");
        switch (method){
            case "add_bmi":
                addBmi();//增加数据
                break;
            case "start_bmi"://获取数据
                //获取数据
                JsonArray array = getBmi();
                writer.print(array);
                break;
            case "delete_bmi"://删除数据
                deleteBmi();
                break;
            default:
                break;
        }
//        writer.println("success");
        writer.flush();
    }

    private void deleteBmi() {
        String sign = this.request.getParameter("sign");
        boolean success = DBHelper.deleteBmi(sign);
        if (success){
            writer.print(1);
        }else {
            writer.print(0);
        }
    }

    private JsonArray getBmi() {
        List<BmiBean> list = DBHelper.getBmi();
        Gson gson = new Gson();
        //使用Gson把list十足转换为String类型的字符串
        String json = gson.toJson(list);
        System.out.println(json);
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        return array;
    }

    private void addBmi() {
        String height = this.request.getParameter("height");
        String weight = this.request.getParameter("weight");
        String bmi = this.request.getParameter("bmi");
        String date = this.request.getParameter("dateTime");
        BmiBean bean = new BmiBean();
        bean.setBmi(bmi);
        bean.setDate(date);
        bean.setHeight(height);
        bean.setWeight(weight);
        boolean success = DBHelper.addBmi(bean);
        if (success){
            JsonArray array = getBmi();
            writer.print(array);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
