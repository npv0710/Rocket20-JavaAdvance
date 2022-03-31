
function loadAccountJs() {
    console.log('Account page js...')
    var dataTable = null
    var flagUpdateAccount = false
    
    var accountId = null

    var PAGE_NUMBER = 1
    var PAGE_SIZE = 20
    var ORDER_BY = 'id,asc'

    function getListDepartments() {
        $.ajax({
            url: 'http://localhost:8888/api/departments',
            method: 'GET',
            
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
                console.log(error)
            }
        })
    }

    getListDepartments()
    
    function getListAccounts(roleInput, departmentIdInput, searchInput, pageNumberInput, pageSizeInput, orderByInput) {
        let url = "http://localhost:8888/api/accounts/paging"
        //let url = "http://localhost:8888/api/accounts/paging?size=" + pageSizeInput + "&sort=" + orderByInput + "&search=" + searchInput
        $.ajax({
            url: url,
            method: 'GET',
            contentType: "application/json",
            dataType: 'json',
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
                //console.log(error)
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
            temp[6] = listAccounts[i].departmentId;
            temp[7] = ''
            temp[8] = ''
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
            ],

            "select": {
                'style': 'multi'
            },
            
        })
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
}


