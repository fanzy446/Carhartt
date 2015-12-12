// JavaScript Document

function appendAnewPhotoBlock(cloId, photoUrl, goodsName, goodsURL, headURL,
		userName, userURL, specialName, specialURL, likeNumber, likeUrl) {
	// /获取那一行
	var col = $(cloId);

	// 1
	var photoBlock = document.createElement("div");
	photoBlock.className = "goodsBlock";
	// 图片加入
	// 2
	var goodsPhoto = document.createElement("div");
	goodsPhoto.className = "goodsPhoto";
	goodsPhoto.setAttribute("onmouseover", "showShare(this);");
	goodsPhoto.setAttribute("onmouseout", "hiddenShare(this);");

	photoBlock.appendChild(goodsPhoto);

	// 3
	var img = document.createElement("img");
	img.src = photoUrl;
	goodsPhoto.appendChild(img);

	// 3-1
	var shareButton = document.createElement("div");
	shareButton.className = "shareButton";
	shareButton.style.display = "none";
	goodsPhoto.appendChild(shareButton);

	// 4
	var shareButton_a = document.createElement("a");
	shareButton_a.setAttribute("href", 'javascript:showTheShareFrame("'
			+ goodsURL + '","' + goodsName + '")');
	shareButton.appendChild(shareButton_a);

	// 5
	var shareButton_img = document.createElement("img");
	shareButton_img.src = "source/image/share.png";
	shareButton_a.appendChild(shareButton_img);

	// 图片加入完成
	photoBlock.appendChild(goodsPhoto);

	// 开始加商品名称

	var goodsNameBlock = document.createElement("div");
	goodsNameBlock.className = "goodsName";
	var goodsName_a = document.createElement("a");
	goodsName_a.innerHTML = goodsName;
	goodsName_a.setAttribute("href", goodsURL);
	goodsNameBlock.appendChild(goodsName_a);
	photoBlock.appendChild(goodsNameBlock);

	// 开始加入商品信息
	var goodsInfromBlock = document.createElement("div");
	goodsInfromBlock.className = "goodsInfrom";

	// 头像
	var headPhotoBlock = document.createElement("div");
	headPhotoBlock.className = "headPhotoBlock";
	var headPhoto_a = document.createElement("a");
	headPhoto_a.setAttribute("href", userURL);
	var headPhoto_img = document.createElement("img");
	headPhoto_img.src = headURL;
	headPhoto_img.className = "headPhoto";
	headPhoto_a.appendChild(headPhoto_img);
	headPhotoBlock.appendChild(headPhoto_a);
	goodsInfromBlock.appendChild(headPhotoBlock);

	// 用户信息
	var userInfromBlock = document.createElement("div");
	userInfromBlock.className = "InformBlock";
	// 分享信息
	var shareInfromBlock = document.createElement("div");
	shareInfromBlock.className = "shareInfrom";
	var shareInfrom_a = document.createElement("a");
	shareInfrom_a.setAttribute("href", userURL);
	shareInfrom_a.innerHTML = userName;
	var shareInfrom_span = document.createElement("span");
	shareInfrom_span.innerHTML = "分享到";
	var shareInfrom_a2 = document.createElement("a");
	shareInfrom_a2.setAttribute("href", specialURL);
	shareInfrom_a2.innerHTML = specialName;
	shareInfromBlock.appendChild(shareInfrom_a);
	shareInfromBlock.appendChild(shareInfrom_span);
	shareInfromBlock.appendChild(shareInfrom_a2);
	userInfromBlock.appendChild(shareInfromBlock);
	// 喜欢信息

	var likeInfromBlock = document.createElement("div");
	likeInfromBlock.className = "likeInfrom";
	var likeInfrom_span1 = document.createElement("span");
	var likeInfrom_span1_a = document.createElement("a");
	likeInfrom_span1_a.setAttribute("href", likeUrl);
	likeInfrom_span1_a.innerHTML = "(" + likeNumber + ")";
	var likeInfrom_span2 = document.createElement("span");
	var likeInfrom_span2_img = document.createElement("img");
	likeInfrom_span2_img.src = "source/image/love.png";
	likeInfromBlock.appendChild(likeInfrom_span1);
	likeInfrom_span1.appendChild(likeInfrom_span1_a);
	likeInfromBlock.appendChild(likeInfrom_span2);
	likeInfrom_span2.appendChild(likeInfrom_span2_img);
	userInfromBlock.appendChild(likeInfromBlock);
	goodsInfromBlock.appendChild(userInfromBlock);
	// 信息完成
	photoBlock.appendChild(goodsInfromBlock);

	col.append(photoBlock);

}
// 返回顶端
function backToTop() {
	$("html,body").animate({
		"scrollTop" : "0"
	}, 50);
	$('.toTop').hide();

}

function showShare(element) {
	element.getElementsByTagName('div')[0].style.display = '';
}

function hiddenShare(element) {
	element.getElementsByTagName('div')[0].style.display = 'none';
}

function hideTheShareFrame() {
	$(".loginFrame").hide();

}

function showTheShareFrame(goodsURL, goodsName) {
	$(".loginFrame").show();
	arr = goodsURL.split('/');
	last = arr.length;
	goodsid = arr[last - 1];
	$("#goodIdtoUP").attr("value", goodsid);
	$(".inputLine h2").html(goodsName);

}

function shareConform() {
	gid = $("#goodIdtoUP").val();
	special = $("#specialSelect").val();
	if (special != "null") {
		$.get("//222.201.132.35:8080/shai/sharecontrol?gid=" + gid + "&album="
				+ special, function(data) {
			hideTheShareFrame();
		});
	}
}

var getFinish = true;
var getNum = 0;

function changePage() {
	var url = window.location.href;

	$(".welcome").show();
/*	$(".nav_list li").attr("class", "nav_item");*/
	// 默认是主页的情况
	$(".newLableBlock").attr("style",
			"background:url(source/image/new.png) no-repeat");
	/*$(".nav_list li").eq(0).attr("class", "nav_item select");*/

	if (url.indexOf("clothing") != -1) {
		$(".welcome").hide();
		$(".newLableBlock").attr("style",
				"background:url(source/image/Clothes.png) no-repeat");
		$(".newLableBlock h1").attr("style", "margin-left:140px;");
		$(".partitioningLine").attr("style", "margin-top:15px;");
		$(".newLableBlock h1").html("服饰");
		$(".nav_list li").eq(1).attr("class", "nav_item select");
	}

	if (url.indexOf("digit") != -1) {
		$(".welcome").hide();
		$(".newLableBlock").attr("style",
				"background:url(source/image/digital.png) no-repeat");
		$(".newLableBlock h1").attr("style", "margin-left:120px;");
		$(".partitioningLine").attr("style", "margin-top:15px;");
		$(".newLableBlock h1").html("数码");
		$(".nav_list li").eq(2).attr("class", "nav_item select");
	}

	if (url.indexOf("amusement") != -1) {
		$(".welcome").hide();
		$(".newLableBlock").attr("style",
				"background:url(source/image/PLAYING.png) no-repeat");
		$(".newLableBlock h1").attr("style", "margin-left:135px;");
		$(".partitioningLine").attr("style", "margin-top:15px;");
		$(".newLableBlock h1").html("其他");
		$(".nav_list li").eq(3).attr("class", "nav_item select");
	}

	if (url.indexOf("culture") != -1) {
		$(".welcome").hide();
		$(".newLableBlock").attr("style",
				"background:url(source/image/culture.png) no-repeat");
		$(".newLableBlock h1").attr("style", "margin-left:135px;");
		$(".partitioningLine").attr("style", "margin-top:15px;");
		$(".newLableBlock h1").html("文化");
		$(".nav_list li").eq(4).attr("class", "nav_item select");
	}

	if (url.indexOf("food") != -1) {
		$(".welcome").hide();
		$(".newLableBlock").attr("style",
				"background:url(source/image/FOODS.png) no-repeat");
		$(".newLableBlock h1").attr("style", "margin-left:115px;");
		$(".partitioningLine").attr("style", "margin-top:15px;");
		$(".newLableBlock h1").html("美食");
		$(".nav_list li").eq(5).attr("class", "nav_item select");

	}

	if (url.indexOf("others") != -1) {
		$(".welcome").hide();
		$(".newLableBlock").attr("style",
				"background:url(source/image/OTHERS.png) no-repeat");
		$(".newLableBlock h1").attr("style", "margin-left:125px;");
		$(".newLableBlock h1").html("其它");
		$(".partitioningLine").attr("style", "margin-top:15px;");
		$(".nav_list li").eq(6).attr("class", "nav_item select");
		;
	}

}

$(document).ready(function() {
	// 判断当前所属的页面
	changePage();

	// 滚动滑条
	$(window).scroll(function() {
		var doc_height = $(document).height();
		var scroll_top = $(document).scrollTop();
		var window_height = $(window).height();
		if (scroll_top + window_height >= doc_height) {
			$("#reading").show();
			// /开始读取一些新的内容
			if (getFinish) {

				getDataFromUrl();
			}

		}
		if (scroll_top >= 600) {
			$('.toTop').show();
		} else {
			$('.toTop').hide();
		}

	});

	getDataFromUrl();

});

// //显示读取完毕////////
function showLaodingEnd() {
	$("#reading li").eq(0).hide();
	$("#reading li").eq(1).html("没有更多内容了");
}

function getMinLengthCol() {
	var returnItem = 1;
	for ( var i = 2; i < 4; i++) {
		if ($('#col' + returnItem).height() > $('#col' + i).height()) {
			returnItem = i;
		}
	}
	return returnItem;

}

// /AJAX加载更多图片
function getDataFromUrl() {
	var url = window.location.href;
	getFinish = false;
	var innerFinish = true;

//	$.get(url + "/pubu?start=" + getNum, function(data) {
//
//		for ( var j = 0; j <= 2; j++) {
//			for ( var i = 1; i <= 4; i++) {
//				var col = "#col" + i;
//
//				item = j * 4 + i - 1;
//
//				if (data.json[item].goodsName == null) {
//					innerFinish = false;
//					showLaodingEnd()
//					break;
//				}
//
//				var goodsName = data.json[item].goodsName;
//				var userID = data.json[item].userid;
//				var goodsId = data.json[item].goodsid;
//				var specialName = data.json[item].specialName;
//				var likeNumber = data.json[item].likeNumber;
//				var userName = data.json[item].userName;
//
//				var photoUrl = "/shai/source/image/p" + goodsId + ".jpg";
//				var goodsURL = "//222.201.132.35:8080/shai/goods/" + goodsId;
//				var headURL = "//222.201.132.35:8080/shai/source/head/" + userID
//						+ ".jpg";
//				var userURL = "//222.201.132.35:8080/shai/person/" + userID;
//				var specialURL = "###################";
//				var likeUrl = 'javascript:like("' + goodsId + '",this)';
//
//				appendAnewPhotoBlock(col, photoUrl, goodsName, goodsURL,
//						headURL, userName, userURL, specialName, specialURL,
//						likeNumber, likeUrl);
//
//			}
//
//		}
//		getNum = getNum + 12;
//		if (innerFinish) {
//			getFinish = true;
//		}
//	});

}

function like(goodsId, element) {
	$.get("//222.201.132.35:8080/shai/likecontrol?gid=" + goodsId, function(data) {

	});
}
