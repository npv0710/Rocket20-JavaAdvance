$(document).ready(function () {
    let menuList = document.getElementsByClassName('sidebar-link')
    
    menuList[1].onclick = function(){
        $('.content').load('home.html')
    }
    menuList[2].onclick = function(){
        $('.content').load('account.html', function() {
            loadAccountJs()
        })
    }
    menuList[3].onclick = function(){
        $('.content').load('department.html')
    }
})