	$(window).ready(function() {
			
			var i = $("#identify").val();
			$("#add").click(function() {
			
			var div = document.createElement("div");
			div.id = "auto" + i
			div.innerHTML = '<span>流水號：'+i+'</span><br><span>顏色：</span><input type=" text " name="color'+i+'" /><p></p><br>'
			+ ' <span>尺寸：</span><input type="text" name="size'+i+'" /><p></p><br>'
			+ '<span>庫存：</span><input type="text" name="stock'+i+'" /><p></p> ';
			$(".auto").append(div);
			$("#identify").attr("value", i);
			i++;
			
			});

			$("#dec").click(function() {
				
					if (i > 0) {
						$("#auto" + i).remove();
						i--;
					}
					$("#identify").attr("value", i);
				});

			});

