
function loadAccountJs() {
    console.log('Account page js...')
    var dataTable = null

    var flagUpdateAccount = false
    var flagDeleteMultipleAccounts = false

    var PAGE_NUMBER = 1
    var PAGE_SIZE = 20
    var ORDER_BY = 'id,desc'//sort paging

    var currentRowIndex = 0;

    var accountId = 0;
    var arrAccountId = []//Mảng lưu các id account khi xóa cùng lúc nhiều bản ghi

    var listAccounts = [] //luu mang account

    function getListDepartments() {
        $.ajax({
            url: 'http://localhost:8888/api/departments',
            method: 'GET',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },

            success: function(response) {
                $.each(response, function(index, option) {   
                    $('#select_department_account')
                        .append($("<option></option>")
                                .attr("value", option.id)
                                .text(option.name));
                    
                    $('#account_department')
                        .append($("<option></option>")
                        .attr("value", option.id)
                        .text(option.name));
                })
            },
            error: function(error) {
                console.log(error.status)
                
            }
        })
    }

    function showErrorAuthentication(msg) {
        $('#error_message_authentication').html(msg)
    }

    getListDepartments()
    
    function getListAccounts(roleInput, departmentIdInput, searchInput, pageNumberInput, pageSizeInput, orderByInput) {
        let url = "http://localhost:8888/api/accounts/paging"
        $.ajax({
            url: url,
            method: 'GET',
            contentType: "application/json",
            dataType: 'json',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            data: {
                role: roleInput,
                departmentId: departmentIdInput,
                search: searchInput,
                pageNumber: pageNumberInput,
                size: pageSizeInput,
                sort: orderByInput
            },
            
            success: function(response){
                //console.log(response)
                createTableAccount(response.content)
            },
            error: function(error) {

                if (error.status == 401) {
                    showErrorAuthentication('Token invalid, please logout and login again to get a new access token!')
                }
            }
        })
    }

    function createTableAccount(listAccounts) {
        let accounts = []

        for (let i = 0; i < listAccounts.length; i++) {
            let temp = []
            temp[0] = i + 1
            temp[1] = listAccounts[i].firstName + " " + listAccounts[i].lastName
            temp[2] = listAccounts[i].username
            temp[3] = listAccounts[i].role
            temp[4] = listAccounts[i].email
            temp[5] = listAccounts[i].departmentName
            temp[6] = listAccounts[i].id;
            temp[7] = ''
            temp[8] = ''
            temp[9] = listAccounts[i].departmentId
            accounts.push(temp)
        }

        dataTable = $('#datatables_accounts').DataTable({
            data: accounts,
            columns: [
                { title: "Order" },
                { title: "Fullname" },
                { title: "Username" },
                { title: "Role" },
                { title: "Email" },
                { title: "Departments"},
                { title: ""},
                { title: "Action" },
                { title: ""},
            ],
            "columnDefs": [
                {
                    "targets": 6,
                    "visible": false
                },
                {
                    "targets": 7,
                    "width": 100,
                    "render": function (data, type, row, meta) {
                        return '<td>' +
                            '<button class="btn-actions edit" data-toggle="modal" data-target="#defaultModalWarning"><i class="material-icons">&#xE3C9;</i></button>' +
                            '<button class="btn-actions delete-account" data-toggle="modal" data-target="#defaultModalDanger"><i class="material-icons">&#xe872;</i></button>' +
                            '</td>'
                    }
                },
                {
                    orderable: false,
                    className: 'select-checkbox',
                    targets: 8,
                    with: 45
                },
                {
                    "targets": 9,
                    "visible": false
                },
            ],

            "select": {
                'style': 'multi'
            },
            
        })

        //Select multil account for deleting
        // $('#datatables_accounts tbody').on( 'click', 'tr', function () {
        //     let id = parseInt(dataTable.row(this).data()[6])
        //     arrAccountId.push(id)
        // });
    }

    //Load accounts & Create table Account
    //getListAccounts()
    getListAccounts('', 0, '', PAGE_NUMBER, PAGE_SIZE, ORDER_BY);

    //Handle actions
    $(document).on('click', '.icon-close', function() {
        $(this).parent().find('select').val('none')
        $(this).parent().find('input').val('')
        $(this).removeClass('icon-close-show')
    })

    $(document).on('change', '.select', function() {
        if ($(this).val() != null) {
            $(this).parent().find('.icon-close').addClass('icon-close-show')
        }
    })

    $(document).on('input', '.search-input input', function() {
        if ($(this).val() !== '') {
            $(this).parent().find('.icon-close').addClass('icon-close-show')
        }else {
            $(this).parent().find('.icon-close').removeClass('icon-close-show')
        }
    })

    $('#account_department').on('change', function() {
        console.log($(this).val())
    })

    //Remove selected row
    $('#btn_close_form_account').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_dismiss_form_account').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_no_account').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_close_delete_account').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    //Filter form
    $('#icon_search_account').on('click', function() {
        let role = $('#select_role_account').val()
        let departmentId = $('#select_department_account').val()
        let search = $('#search_input_account').val()
        
        console.log('departmentId: ' + departmentId)
        console.log(role)

        //Note
        if (departmentId === 'none') departmentId = 0
        else departmentId = parseInt(departmentId)

        if (search === '') search = null
        else {
            search = search.trim().replace(' ', '%')
        }
        if (role === 'none') role = ''

    
        dataTable.destroy()
    
        getListAccounts(role, departmentId, search, 1, PAGE_SIZE, ORDER_BY);
    })

    //btn delete only one account
    $(document).on('click', 'td .delete-account', function() {

        flagDeleteMultiAccount = false;

        currentRowIndex = parseInt( $(this).closest('tr').find('td').eq(0).html() ) - 1

        let rowData = dataTable.row(currentRowIndex).data()

        accountId = rowData[6]
    })


    //Clear arrAccountId after delete multiple
    const clearArrAccountId = () => {
        while(arrAccountId.length > 0) {
            arrAccountId.pop()
        }
    } 

    //btn delete mutiple accounts
    $('#btn_delete_multiple_account').click(function() {
        flagDeleteMultipleAccounts = true

        //Check rows selected
        if (dataTable.rows('.selected').data().length == 0) {
            alert('Mời bạn chọn account muốn xóa')
        }else {
            flagDeleteMultiAccount = true
            $('#defaultModalDanger').modal()
        }
    })

    $('#btn_confirm_delete_account').click(function() {
        if (flagDeleteMultiAccount == true) {
            //Select multil account for deleting
            let accountsSelected = dataTable.rows('.selected').data();

            if (accountsSelected.length > 0) {
                for (let i = 0; i < accountsSelected.length; i ++) {
                    arrAccountId.push(accountsSelected[i][6])
                }
            }

            $.ajax({
                url: "http://localhost:8888/api/accounts/delete-multiple",
                method: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                },
                data: JSON.stringify(arrAccountId),
                success: function(response) {
                    $('#btn_no_account').click()//close modal

                    //Refresh table
                    dataTable.destroy()
                    getListAccounts('', 0, '', 1, PAGE_SIZE, ORDER_BY)

                    //Reset arr accounts after delete
                    clearArrAccountId()
                },
                error: function(request, status, error) {
                    console.log(error)
                    //Reset arr accounts after delete
                    clearArrAccountId()

                    //Deselect all on the data table
                    dataTable.$('tr.selected').removeClass('selected');
                }
            })
        }else {
            $.ajax({
                url: 'http://localhost:8888/api/accounts/delete/' + accountId,
                method: 'POST',
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                },
                success: function(response) {
                    $('#btn_no_account').click()
                    dataTable.row(currentRowIndex).remove().draw()
                },
                error: function(request, status, error) {
                    console.log(error)
                }
            })
        }
    })

    //edit account
    $('#datatables_accounts').on('click', 'td .edit', function(e) {
        
        e.preventDefault()

        $('#form_group_email').hide()
        $('#form_group_passowrd').hide()

        $('#title_form_account').html('Edit Account')

        flagUpdateAccount = true

        currentRowIndex = parseInt( $(this).closest('tr').find('td').eq(0).html() ) - 1

        //console.log('current row index: ' + currentRowIndex)
        
        let rowData = dataTable.row(currentRowIndex).data()
        //console.log(rowData)

        //Get accountId(varibale global)
        accountId = rowData[6]

        let username = rowData[2]
        let fullName = rowData[1]

        let firstName = ''
        let lastName = ''

        let temp = fullName.trim().split(' ');
        if (temp.length == 2) {
            firstName = temp[0]
            lastName = temp[1]
        }else if (temp.length == 3) {
            firstName = temp[0] + ' ' + temp[1]
            lastName = temp[2]
        }else if (temp.length = 1) {
            firstName = temp[0]
            lastName = ''
        }

        let role = rowData[3]
        let departmentId = parseInt(rowData[9])

        $('#user_name').val(username)
        $('#first_name').val(firstName)
        $('#last_name').val(lastName)
        $('#role').val(role)
        $('#account_department').val(departmentId)
    })

    $('#btn_save_account').click(function(e) {
        e.preventDefault()

        let data = {
            username: $('#user_name').val(),
            firstName: $('#first_name').val(),
            lastName: $('#last_name').val(),
            role: $('#role').val(),
            departmentId: $('#account_department').val()
        }

        let url = ''
        let methodRequest = ''
        if (flagUpdateAccount == true) {
            url = 'http://localhost:8888/api/accounts/' + accountId
            methodRequest = 'PUT'
        }else {
            url = 'http://localhost:8888/api/accounts'
            methodRequest = 'POST'

            //Add email, password properties to account
            data.email = $('#email').val()
            data.password = $('#password').val()

            console.log(data)
        }

        $.ajax({
            url: url,
            method: methodRequest,
            contentType: 'application/json',
            dataType: 'json',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            data: JSON.stringify(data),
            success: function(response) {
                //Close modal
                $('#btn_dismiss_form_account').click()
                
                //update row data
                if (flagUpdateAccount == true) {
                    let temp = []
                    temp[0] = currentRowIndex + 1
                    temp[1] = data.firstName + " " + data.lastName
                    temp[2] = data.username
                    temp[3] = data.role
                    temp[4] = dataTable.row(currentRowIndex).data()[4]
                    temp[5] = $('#account_department option:selected').text()
                    temp[6] = dataTable.row(currentRowIndex).data()[6];
                    temp[7] = ''
                    temp[8] = ''
                    temp[9] = data.departmentId
                    
                    dataTable.row(currentRowIndex).data(temp).draw(false)
                }else {
                    //Refresh table account after insert new item
                    dataTable.destroy()

                    getListAccounts('', 0, '', PAGE_NUMBER, PAGE_SIZE, ORDER_BY);
                }
            },
            error: function(request, status, error) {
                console.log(error)
            }
        })

    })

    //Create account
    $('#btn_add_account').click(function(e) {
        
        e.preventDefault()

        console.log('123abc')

        flagUpdateAccount = false

        //Show input emial && password
        $('#form_group_email').show()
        $('#form_group_passowrd').show()

        $('#title_form_account').html('Add New Account')

        $('#user_name').val('')
        $('#first_name').val('')
        $('#last_name').val('')
        $('#email').val('')
        $('#password').val('')
        $('#role').val('none')
        $('#account_department').val('none')
    })




}


