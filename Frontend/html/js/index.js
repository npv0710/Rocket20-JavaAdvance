$(document).ready(function () {
    let menuList = document.getElementsByClassName('sidebar-link')
    // Lặp qua từng phần tử trong kết quả và gán sự kiện
    menuList[1].onclick = function(){
        $('.content').load('home.html')
        //loadHome()
    }
    menuList[2].onclick = function(){
        $('.content').load('account.html')
        loadAccount()
    }
    menuList[3].onclick = function(){
        $('.content').load('department.html')
        //loadDepartment()
    }
})