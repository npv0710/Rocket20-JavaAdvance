function loadAccount() {
    console.log('account page js...')
    var dataTable = null
    var flagUpdateAccount = false
    
    var accountId = null

    function getListAccounts() {
        let url = "http://localhost:8888/api/accounts"
        $.ajax({
            url: url,
            type: 'GET',
            contentType: "application/json",
            dataType: 'json',
            // beforeSend: function (xhr) {
            //     xhr.setRequestHeader("Authorization", "Basic " + btoa(localStorage.getItem("Username") + ":" + localStorage.getItem("Password")));
            // },
            success: function(response){
                console.log(response)
                createTableAccount(response)
            },
            error: function(error) {
                console.log(error)
            }
        })
    }

    function createTableAccount(listAccounts) {
        let accounts = []

        for (let i = 0; i < listAccounts.length; i++) {
            let temp = []
            //temp[0] = i + 1
            temp[0] = listAccounts[i].firstName + " " + listAccounts[i].lastName
            temp[1] = listAccounts[i].username
            temp[2] = listAccounts[i].role
            temp[3] = listAccounts[i].email
            temp[4] = ''
            temp[5] = ''
            accounts.push(temp)
        }

        dataTable = $('#datatables_accounts').DataTable({
            data: accounts,

            columns: [
                { title: "Fullname" },
                { title: "Username" },
                { title: "Role" },
                { title: "Email" },
                { title: "Action" }
            ],
            "columnDefs": [
                {
                    "targets": 4,
                    "width": 100,
                    "render": function (data, type, row, meta) {
                        return '<td>' +
                            '<button class="btn-actions edit" data-toggle="modal" data-target="#defaultModalPrimary"><i class="material-icons">&#xE3C9;</i></button>' +
                            '<button class="btn-actions delete-account" data-toggle="modal" data-target="#defaultModalDanger"><i class="material-icons">&#xe872;</i></button>' +
                            '</td>'
                    }
                },
                {
                    orderable: false,
                    className: 'select-checkbox',
                    targets: 5,
                    with: 45
                },
            ],

            "select": {
                'style': 'multi'
            },
            
        })
    }

    //Load accounts & Create table Account
    getListAccounts()

    //Handle actions
    $('#datatables-accounts').on('click', '.edit', function(e){
        e.preventDefault()

        flagUpdateAccount = true

        let index = parseInt($(this).parents('tr').find('td').eq(1).html()) - 1
        console.log(index)

        let record = dataTable.row(index).data()
        let fullName = record[0]
        let firstName = fullName.substr(0, fullName.indexOf(' ').trim())
        let lastName = fullName.substr(fullName.indexOf(' ').trim())
        let userName = record[1]
        let role = record[2]
        let departmentName = record[3]
        
        accountId = 1
        $('user_name').val(userName)
        $('first_name').val(firstName)
        $('last_name' ).val(lastName)
        $('role').val(role)
        $('department').val(departmentName)



    })

}