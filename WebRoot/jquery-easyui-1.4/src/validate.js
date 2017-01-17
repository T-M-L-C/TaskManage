
function validate(){
     var authority=getCookieValue("authority");
     if(authority!="%u7CFB%u7EDF%u7BA1%u7406%u5458"){
    	 alert('抱歉，你不具备进行该操作的权限 ');
    	 return false;
     }
     return true;
}