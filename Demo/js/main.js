function commitData(){
	//1.获取身高和体重    document内置对象：当html文档加载完毕之后，会自动生成一个document对象
	//getElementById:通过id查找标签
	//value获取标签的值
	var height = document.getElementById("height_num").value;
	var weight = document.getElementById("weight_num").value;
	if(jQuery.isEmptyObject(height) || jQuery.isEmptyObject(weight)){
		alert("请输入身高和体重");
		return;
	}else{
		if(height > 250 || weight > 200){
			alert("您输入的身高和体重不合理！");
			return;
		}
	}
	//2.进行计算   bmi=体重/(身高^2)
	console.log("height:" + height);
	console.log("weight:" + weight);
	var bmiNum = weight / ((height / 100) * (height / 100));
	//Math.round取整
	bmiNum = Math.round(bmiNum * 100) / 100;
	console.log("bmiNum" + bmiNum);
	//建议体重 weight=bmi*height*height [18.5~23.9]
	var weiMin = 18.5 * (height / 100) * (height / 100);
	weiMin = Math.round(weiMin * 100) / 100;
	var weiMax = 23.9 * (height / 100) * (height / 100);
	weiMax = Math.round(weiMax * 100) / 100;
	//3.显示到当前页面 
	//innerHTML可以对标签添加文本或者标签
	document.getElementById("bmi").innerHTML = "您的BMI值：" + bmiNum;
	document.getElementById("wei").innerHTML = "您的理想体重：" + weiMin + "~" + weiMax + "kg之间";
	if(bmiNum <= 18.4){
		alert("您目前处于偏瘦，需要多吃肉！");
	}else if(bmiNum < 23.9){
		alert("您目前处于完美身材，继续保持哦！");
	}else if(bmiNum < 27.9){
		alert("您目前处于过重，多吃青菜少吃肉！");
	}else{
		alert("您目前处于肥胖，少吃点饭哦！");
	}
	var date = new Date();
	var dateTime = date.toLocaleString();
	//4.提交后台
	$.ajax({
		type:"post",//请求方式 post/get
		url:"http://127.0.0.1:8080/BMI/bmi?method=add_bmi",//请求的地址
		//变量名：变量值 参数与参数之间使用","隔开
		data:{
			height:height,
			weight:weight,
			bmi:bmiNum,
			dateTime:dateTime
		},
		async:false,//是否同步
		timeout:5000,//设置超时时间
		dataType:"json",
		success:function(data){
			//data:后台打印回来的数据
			//alert(data)
			if(!jQuery.isEmptyObject(data)){
				//更新的数据显示到前端页面
				addBmiHistory(data[data.length - 1]);
			}else{
				alert("数据存储失败！")
			}
		},
		error:function(xhr,textState){
			alert("数据请求失败！");
		}
	});
}
//页面所有元素加载完以后，执行此方法
$(document).ready(function(e){
	$.ajax({
		type:"post",
		url:"http://127.0.0.1:8080/BMI/bmi?method=start_bmi",
		async:false,
		timeout:5000,
		dataType:"json",
		success:function(data){
			if(!jQuery.isEmptyObject(data)){
				for (var i = 0;i < data.length;i　++) {
					addBmiHistory(data[i]);
				}
			}
		},
		error:function(xhr,textState){
			alert("请求失败！");
		}
	});
});
function addBmiHistory(data){
	var table2 = document.getElementById("table2");
	//通过document创建一个tr
	var tr1 = document.createElement("tr");
	//创建的tr放到table2中
	table2.appendChild(tr1);
	//给tr添加标签一个唯一标识
	//给每个tr添加
	tr1.id = data.id;
	//innerHTML不仅可以添加文本还可以添加标签
	tr1.innerHTML =
	"<td>" + 
	data.id + "</td><td>" +
	data.date + "</td><td>" + 
	data.height + "</td><td>" + 
	data.weight + "</td><td>" + 
	data.bmi + "</td><td><a href='#' onclick='deleteData(" + data.id + ")'>删除</a>" + 
	"</td>";
}
//  通过追加的方式
//	var td1 = document.createElement("td");
//	td1.innerHTML = data[i].id;
//	tr1.appendChild(td1);
function deleteData(sign){
	$.ajax({
		type:"post",
		url:"http://127.0.0.1:8080/BMI/bmi?method=delete_bmi",
		data:{
			sign:sign
		},
		async:false,
		timeout:5000,
		dataType:"text",
		success:function(data){
			if(data == 1 || data == "1"){
//				var del_tr = document.getElementById(sign);
//				var table2 = document.getElementById("table2");
//				table2.removeChild(del_tr);
				//移除
				$("#" + sign).remove();
				 alert("删除成功！");
			}else{
				alert("删除失败！");
			}
		},
		error:function(xhr,textState){
			alert("请求失败");
		}
	});
}
