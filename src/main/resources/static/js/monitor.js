function drawLayer02Label(canvasObj,text,textBeginX,lineEndX){
	var colorValue = '#04918B';

	var ctx = canvasObj.getContext("2d");

	ctx.beginPath();
	ctx.arc(35,55,2,0,2*Math.PI);
	ctx.closePath();
	ctx.fillStyle = colorValue;
	ctx.fill();

	ctx.moveTo(35,55);
	ctx.lineTo(60,80);
	ctx.lineTo(lineEndX,80);
	ctx.lineWidth = 1;
	ctx.strokeStyle = colorValue;
	ctx.stroke();

	ctx.font='12px Georgia';
	ctx.fillStyle = colorValue;
	// ctx.fillText(text,textBeginX,92);
    ctx.strokeText(text,textBeginX,92);
}





//存储
function renderLayer03Right(data_1,data_2,data_3){
	drawLayer03Right($("#layer03_right_chart01 canvas").get(0),"#027825",data_1);
	drawLayer03Right($("#layer03_right_chart02 canvas").get(0),"#006DD6",data_2);
	drawLayer03Right($("#layer03_right_chart03 canvas").get(0),"#238681",data_3);
}
//圆
function drawLayer03Right(canvasObj,colorValue,rate){
	var ctx = canvasObj.getContext("2d");
    
	var circle = {
        x : 65,    //圆心的x轴坐标值
        y : 80,    //圆心的y轴坐标值
        r : 60      //圆的半径
    };

	//画扇形
	//ctx.sector(circle.x,circle.y,circle.r,1.5*Math.PI,(1.5+rate*2)*Math.PI);
	//ctx.fillStyle = colorValue;
	//ctx.fill();

	ctx.beginPath();
	ctx.arc(circle.x,circle.y,circle.r,0,Math.PI*2)
	ctx.lineWidth = 10;
	ctx.strokeStyle = '#052639';
	ctx.stroke();
	ctx.closePath();

	ctx.beginPath();
	ctx.arc(circle.x,circle.y,circle.r,1.5*Math.PI,(1.5+rate*2)*Math.PI)
	ctx.lineWidth = 10;
	ctx.lineCap = 'round';
	ctx.strokeStyle = colorValue;
	ctx.stroke();
	ctx.closePath();
    
	ctx.fillStyle = 'white';
	ctx.font = '20px Calibri';
	ctx.fillText(rate*100+'%',circle.x-15,circle.y+10);

}

//折线图
function renderLayer04Right(){
	var myChart = echarts.init(document.getElementById("layer04_right_chart"));
	myChart.setOption({
			title: {
				text: ''
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				top:20,
				right:5,
				textStyle:{
					color:'white'
				},
				orient:'vertical',
				data:[
						{name:'基板',icon:'circle'},
						{name:'火焰',icon:'circle'},
						{name:'槽内',icon:'circle'}
					]
			},
			grid: {
				left: '3%',
				right: '16%',
				bottom: '3%',
				top:'3%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				axisTick:{show:false},
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
						}
				},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				data: get10MinutesScale()
			},
			yAxis: {
				type: 'value',
				axisTick:{show:false},
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
						}
				},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				splitLine:{
					show:false
				}
			},
			series: [
						{
							name:'基板',
							type:'line',
							itemStyle : {  
									normal : {  
									color:'#F3891B'
								},
								lineStyle:{
									normal:{
									color:'#F3891B',
									opacity:1
										}
								}
							},  
							data:[0, 0, 0, 0, 0, 0, 0]
						},
						{
							name:'火焰',
							type:'line',
							itemStyle : {  
									normal : {  
									color:'#006AD4'
								},
								lineStyle:{
									normal:{
									color:'#F3891B',
									opacity:1
										}
								}
							},
							data:[0, 0, 0, 0, 0, 0, 0]
						},
						{
							name:'槽内',
							type:'line',
							itemStyle : {  
									normal : {  
									color:'#009895'
								},
								lineStyle:{
									normal:{
									color:'#009895',
									opacity:1
										}
								}
							},
							data:[0, 0, 0, 0, 0, 0, 0]
						}
					]
		}	
	);


    var ctx1 = $("#layer03_right_chart01 canvas").get(0).getContext("2d");
    var ctx2 = $("#layer03_right_chart02 canvas").get(0).getContext("2d");
    var ctx3 = $("#layer03_right_chart03 canvas").get(0).getContext("2d");
    var circle = {
        x : 65,    //圆心的x轴坐标值
        y : 80,    //圆心的y轴坐标值
        r : 60      //圆的半径
    };



	setInterval(function () {		
		$.ajax({
            type : "post",
            async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "/getList",    //请求发送到dataActiont处
            data : {},
            dataType : "json",        //返回数据形式为json
            success : function(result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result) {					
					myChart.setOption({
                        xAxis: {
                            data: [result[6].addtime,result[5].addtime,result[4].addtime,result[3].addtime,result[2].addtime,result[1].addtime,result[0].addtime]
                        },
						series: [
						{
							name:'基板',
							data: [result[6].intankTem,result[5].intankTem,result[4].intankTem,result[3].intankTem,result[2].intankTem,result[1].intankTem,result[0].intankTem]
						},
						{
							name:'火焰',
							data: [result[6].flameTem,result[5].flameTem,result[4].flameTem,result[3].flameTem,result[2].flameTem,result[1].flameTem,result[0].flameTem]
						},
						{
							name:'槽内',
							data: [result[6].substraTetem,result[5].substraTetem,result[4].substraTetem,result[3].substraTetem,result[2].substraTetem,result[1].substraTetem,result[0].substraTetem]
						}]
					});

                    $("#layer03_right_chart01 canvas").get(0).getContext("2d").clearRect(0,0,130,150);
                    $("#layer03_right_chart02 canvas").get(0).getContext("2d").clearRect(0,0,130,150);
                    $("#layer03_right_chart03 canvas").get(0).getContext("2d").clearRect(0,0,130,150);
                    renderLayer03Right(result[0].alcoAllowance*0.01, result[0].alcoConcent*0.01, 0.3);

                    document.getElementById("layer02_03_span").textContent=result[0].flameTem;
                    document.getElementById("layer02_04_span").textContent=result[0].alcoConcent;
                    //document.getElementById('p1').innerText = result.achId;
                }


            },
            error : function() {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
            }
        });//end ajax
		
	}, 1000);
}

function get10MinutesScale()
{
	var currDate = new Date();
	var odd = currDate.getMinutes()%10;
	var returnArr = new Array();
	currDate.setMinutes(currDate.getMinutes()-odd);
	for(var i = 0; i <7; i++){
		returnArr.push(currDate.getHours()+":"+(currDate.getMinutes()<10?("0"+currDate.getMinutes()):currDate.getMinutes()));
		currDate.setMinutes(currDate.getMinutes()-10);
	}
	return returnArr;
}
