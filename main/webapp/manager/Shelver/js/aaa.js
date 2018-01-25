	$(window).ready(function() {
			var i = 0;
			$("#add").click(function() {
			i++
			var div = document.createElement("div");
			div.style.marginBottom = "15px";
			div.style.marginTop = "15px";
			div.id = "auto" + i
			div.innerHTML = '<span>流水號：'+i+'</span><br><span>顏色：</span><input type=" text " name="color'+i+'" /><p></p><br>'
			+ ' <span>尺寸：</span><input type="text" name="size'+i+'" /><p></p><br>'
			+ '<span>庫存：</span><input type="text" name="stock'+i+'" /><p></p> ';
			$(".auto").append(div);
			$("#identify").attr("value", i);
			});

			$("#dec").click(function() {
				$("#auto" + i).remove();
					if (i > 0) {
						i--;
					}
					$("#identify").attr("value", i);
				});

			});

