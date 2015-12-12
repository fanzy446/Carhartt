window.onload = function() {
	getboardinfo();
	var btnDeleteChose = document.getElementById('btnDeleteChose');
	btnDeleteChose.onclick = function() {
		// var fav = document.getElementById('item_list').fav_checkbox;
		var favorites_Items_Id_Str = "";
		if (document.all("fav_checkbox") != undefined) {
			// 将checkbox的勾选结果转换成字符串favorites_Items_Id_Str
			// 如果没有元素被选中结果为'',如果有元素被选中结果为'id1 id2 '
			if (document.all("fav_checkbox").length == undefined) {// 只有一个元素
				if (document.all("fav_checkbox").checked) {// 这个元素被选中
					favorites_Items_Id_Str = document.all("fav_checkbox").value
							+ " ";
				}
			} else {// 有不只一个元素
				for (i = 0; i < document.all("fav_checkbox").length; ++i) {
					if (document.all("fav_checkbox")[i].checked) {
						favorites_Items_Id_Str += document.all("fav_checkbox")[i].value
								+ " ";
					}
				}
			}

			if (favorites_Items_Id_Str.length != 0) {
				favorites_Items_Id_Str = favorites_Items_Id_Str.substr(0,
						favorites_Items_Id_Str.length - 1);
				favoriteIds = favorites_Items_Id_Str.split(" ");
				for (i = 0; i < favoriteIds.length; i++) {
					var favoriteId = favoriteIds[i].substr(0, favoriteIds[i]
									.indexOf("_"));
					var btn_del_id = "btn_del_" + favoriteId;
					console.log(btn_del_id);
					document.getElementById(btn_del_id).click();
				}
				alert("删除成功！");

			} else {
				console.log("没有选中元素");
			}

		} else {
			console.log("没有收藏元素");
		}
	}

	// 收藏按钮
	var btnAddCartChose = document.getElementById('btnAddCartChose');
	btnAddCartChose.onclick = function() {
		// var fav = document.getElementById('item_list').fav_checkbox;
		var favorites_Items_Id_Str = "";
		if (document.all("fav_checkbox") != undefined) {
			// 将checkbox的勾选结果转换成字符串favorites_Items_Id_Str
			// 如果没有元素被选中结果为'',如果有元素被选中结果为'id1 id2 '
			if (document.all("fav_checkbox").length == undefined) {// 只有一个元素
				if (document.all("fav_checkbox").checked) {// 这个元素被选中
					favorites_Items_Id_Str = document.all("fav_checkbox").value
							+ " ";
				}
			} else {// 有不只一个元素
				for (i = 0; i < document.all("fav_checkbox").length; ++i) {
					if (document.all("fav_checkbox")[i].checked) {
						favorites_Items_Id_Str += document.all("fav_checkbox")[i].value
								+ " ";
					}
				}
			}

			if (favorites_Items_Id_Str.length != 0) {
				favorites_Items_Id_Str = favorites_Items_Id_Str.substr(0,
						favorites_Items_Id_Str.length - 1);
				favoriteIds = favorites_Items_Id_Str.split(" ");
				console.log(favoriteIds + "===");
				for (i = 0; i < favoriteIds.length; i++) {
					var itemId = favoriteIds[i].substr(favoriteIds[i]
							.indexOf("_")
							+ 1);
//					var btn_add_id = itemId;
//					document.getElementById(itemId).click();

					$.ajax({
								url : "../../../addToCart",
								type : 'post',
								dataType : 'json',
								data : {
									itemId : itemId
								},
								success : function() {
									console.log("add cart success");
								},
								error : function() {
									console.log("add cart error!");
								}
							});
				}
				alert("加入购物车成功");
			} else {
				console.log("没有选中元素");
			}

		} else {
			console.log("没有收藏元素");
		}
	};

	function getFavorites() {
		$.ajax({
			url : '../../../getFavorites',
			type : 'post',
			dataType : 'json',
			data : {
				userid : 1
			},
			success : function(data) {
				var favorites = data['favorites'];
				$.each(favorites, function(i, item) {

							var li = document.createElement("li");
							li.id = item.favoriteId;

							var a_img = document.createElement("a");
							a_img.href = "productDetail.jsp?itemId="
									+ item.itemId;
							var img = document.createElement("img");
							img.src = "../../../" + item.itemPhoto;
							a_img.appendChild(img);

							var span = document.createElement("span");
							var input = document.createElement("input");
							input.type = "checkbox";
							input.name = "fav_checkbox";
							input.value = item.favoriteId + "_" + item.itemId;
							var p = document.createElement("p");
							var inventory = item.itemCount > 0 ? "有货" : "缺货";
							p.innerHTML = "&nbsp;&nbsp;&nbsp;&yen;"
									+ item.itemPrice + "&nbsp;&nbsp;"
									+ inventory;
							span.appendChild(input);
							span.appendChild(p);

							var a1 = document.createElement("a");
							a1.innerHTML = "&nbsp;&nbsp;加入购物车&nbsp;&nbsp;";
							a1.id = item.itemId;
							a1.onclick = function(e) {
								var itemId = e.srcElement.getAttribute("id");
								$.ajax({
											url : "../../../addToCart",
											type : 'post',
											dataType : 'json',
											data : {
												itemId : itemId
											},
											success : function() {
												console.log("add cart success");
												alert("加入购物车成功");
											},
											error : function() {
												console.log("add cart error!");
												alert("加入购物车失败");
											}
										});
							};
							var a2 = document.createElement("a");
							a2.innerHTML = "删除";
							a2.id = "btn_del_" + item.favoriteId;
							a2.onclick = function(e) {
								var favoriteId = e.srcElement.parentNode
										.getAttribute("id");
								e.srcElement.parentNode.remove();
								$.ajax({
											url : "../../../delFavorite",
											type : 'post',
											dataType : 'json',
											data : {
												favoriteId : favoriteId
											},
											success : function() {
												console.log("delete success");
											},
											error : function() {
												console.log("delete error!");
											}
										});
							};

							li.appendChild(a_img);
							li.appendChild(span);
							li.appendChild(a1);
							li.appendChild(a2);

							$('#item_list').append(li);
						});
			},
			error : function() {
				alert("error");
			}
		});
	}
	getFavorites();
};

function select_all() {
	var oList = document.getElementById('item_list');
	var aToSelect = oList.getElementsByTagName('input');
	var i = 0;
	for (i = 0; i < aToSelect.length; i++) {
		if (aToSelect[i].type == 'checkbox') {
			aToSelect[i].checked = true;
		}
	}
};
function de_select_all() {
	var oList = document.getElementById('item_list');
	var aToSelect = oList.getElementsByTagName('input');
	var i = 0;
	for (i = 0; i < aToSelect.length; i++) {
		if (aToSelect[i].type == 'checkbox') {
			aToSelect[i].checked = false;
		}
	}
};