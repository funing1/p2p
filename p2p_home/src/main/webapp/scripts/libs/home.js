function isPlaceholder(){
    var input = document.createElement('input');
    return 'placeholder' in input;
}
if (!isPlaceholder()) {
    $(document).ready(function() {
        if(!isPlaceholder()){
            $("input").not("input[type='password']").each(
                function(){
                    if($(this).val()=="" && $(this).attr("placeholder")!=""){
                        $(this).val($(this).attr("placeholder"));
                        $(this).focus(function(){
                            if($(this).val()==$(this).attr("placeholder")) $(this).val("");
                        });
                        $(this).blur(function(){
                            if($(this).val()=="") $(this).val($(this).attr("placeholder"));
                        });
                    }
                });
            //对password框的特殊处理
            var pwdField	= $("input[name=password]");
            var pwdVal		= pwdField.attr('placeholder');
            pwdField.after('<input id="pwdPlaceholder" type="text" value='+pwdVal+' class="input-text" autocomplete="off" >');
            var pwdPlaceholder = $('#pwdPlaceholder');
            pwdPlaceholder.show();
            pwdField.hide();
	        
            pwdPlaceholder.focus(function(){
                pwdPlaceholder.hide();
                pwdField.show();
                pwdField.focus();
            });
	        
            pwdField.blur(function(){
                if(pwdField.val() == '') {
                    pwdPlaceholder.show();
                    pwdField.hide();
                }
            });
        }
    });
}
$(document).ready(function() {
    $('.menu_list').find('a[rel=title]').click(function(){
        $(this).parent().toggleClass('cur');
        $(this).siblings('.subMenu').toggle();
    });
    $('.subMenu a').click(function(){
        $(this).parents('ul').find('a').removeClass('hover');
        $(this).addClass('hover');
    });
});
