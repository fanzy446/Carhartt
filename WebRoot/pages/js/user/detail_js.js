

// JavaScript Document
function appendAnewPhotoBlock(headURL,userName,comment,commentTime,userUrl){
	var detailContent=$(".detail_remark");
	var detailBlock=document.createElement("div");
	detailBlock.className="detail_one_remark_block";
	
	
	var headDiv=document.createElement("div");
	headDiv.className="detail_remark_leftHead_40";
	var headImg=document.createElement("img");
	headImg.src=headURL;
	headDiv.appendChild(headImg);
	detailBlock.appendChild(headDiv);
	

	
	var CommonContent=document.createElement("div");
	CommonContent.className="detail_remark_content";
	var a1=document.createElement("a");
	a1.setAttribute("href",userUrl);
	a1.innerHTML=userName;
	var h2=document.createElement("h2");
	h2.innerHTML=comment;
	var h3=document.createElement("h3");
	h3.innerHTML=commentTime;
	CommonContent.appendChild(a1);
	CommonContent.appendChild(h2); 
	CommonContent.appendChild(h3);
	
	
	detailBlock.appendChild(CommonContent);
	detailContent.append(detailBlock);
}

var commentPage=0;

$(document).ready(function(){  
	
	
	$("#nextPage").attr("style","float:right;width:72px;");
	
	$("#prePage").attr("style","float:left;width:72px;");
	
	hidePrePage();
	
	getDataFromUrl(commentPage);
});

var url=window.location.href;
var pre=false;
var next=true;
function getDataFromUrl(pageNum){
	
	$("#nomoreAlerk").hide();
	 $.get(url+"/comment"+"?start="+pageNum*6,function(data){
		 
		 for(var item=0;item<6;item++){
			 	if (data.json[item].username==null){
			 		hideNextPage();
			 		$("#nomoreAlerk").show();
			 		break;
			 	}
				var userName=data.json[item].username; 
				var comment=data.json[item].content; 
				var commentTime=data.json[item].datetime;
				var userid=data.json[item].userid;
				var headURL="//222.201.132.35:8080/shai/source/head/"+userid+".jpg";
				var userUrl="//222.201.132.35:8080/shai/person/"+userid;
				appendAnewPhotoBlock(headURL,userName,comment,commentTime,userUrl);
			 
		 }
		$(".detail_remark").show();
		$(".readingCommon").hide();
	 });
	
}


function nextPage(){
	commentPage++;
	if (commentPage==1){
		showPrePage();
	}
	$(".detail_remark").hide();
	$(".detail_remark").html("");
	$(".readingCommon").show();
	getDataFromUrl(commentPage);
}

function prePage(){
	commentPage--;
	if (commentPage==0){
		hidePrePage();
	}
	if (!next){
		showNextPage();
	}
	$(".detail_remark").hide();
	$(".detail_remark").html("");
	$(".readingCommon").show();
	getDataFromUrl(commentPage);
}


function hideNextPage(){
	
	$("#nextPage").hide();
	next=false;
}


function showNextPage(){
	$("#nextPage").show();
	next=true;
}

function hidePrePage(){
	pre=false;
	$("#prePage").hide();
}


function showPrePage(){
	$("#prePage").show();
	pre=true;
}



