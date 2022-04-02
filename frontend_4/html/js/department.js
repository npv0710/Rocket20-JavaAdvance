function loadDepartmentJS () {
    var dataTable = null
    
    var dataTableAccount = null

    var departmentId = null
    var arrDepartmentId = []

    var arrAccounts = []

    var flagDeleteMultiDepartment = false
    
    var flagCreateNewDepartment = false

    var currentRowIndex = 0

    var PAGE_NUMBER = 1
    var PAGE_SIZE = 10
    var ORDER_BY = 'id,desc'

    function getListDepartments(minDateInput, maxDateInput, typeInput, searchInput, pageNumberInput, pageSizeInput, orderByInput) {
        $.ajax({
            url: 'http://localhost:8888/api/departments/paging',
            method: 'GET',

              /* Basic authentication */
            // beforeSend: function(xhr) {
            //     xhr.setRequestHeader('Authorization', 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password')))
            // },

            /* JWT authentication */

            data: {
                minDate: minDateInput,
                maxDate: maxDateInput,
                type: typeInput,
                search: searchInput,
                pageNumber: pageNumberInput,
                size: pageSizeInput,
                sort: orderByInput
            },
            success: function(response) {
                console.log(response)
                createTableDepartment(response.content)//Create datatable department
            },
            error: function(error) {
                console.log(error)
            }
        })
    }

    getListDepartments('', '', '', '', PAGE_NUMBER, PAGE_SIZE, ORDER_BY)

    function createTableDepartment(listDepartments) {
        console.log(listDepartments)
        let departments = []
        for (let i = 0; i < listDepartments.length; i ++) {
            let temp = []
            temp[0] = i + 1
            temp[1] = listDepartments[i].name
            temp[2] = listDepartments[i].totalMember
            temp[3] = listDepartments[i].type
            temp[4] = listDepartments[i].createdAt
            temp[5] = listDepartments[i].id
            temp[6] = ''
            temp[7] = '',
            departments.push(temp)
        }
        
        dataTable = $('#datatables_departments').DataTable({
            'processing': true,
            data: departments,
            columns: [
                { title: 'Order'},
                { title: "Name"},
                { title: "Total Member"},
                { title: "Type"},
                { title: "Created Date"},
                { title: ''},
                { title: "Actions"},
                { title: "Select"},
            ],
            "columnDefs": [
                {
                    "targets": 5,
                    "visible": false
                },
                {
                    "targets": 6,
                    "width": 110,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        $(td).css({
                            'text-align': 'left',
                            'display': 'flex'
                        });
                    },
                    "render": function(data, type, row, meta) {
                        return '<td>' +
                            '<button data-toggle="modal" data-target="#defaultModalWarning2" class="edit btn-modal"><i class="material-icons">&#xE254;</i></button>' +
                            '<button data-toggle="modal" data-target="#defaultModalDanger2" class="delete btn-modal"><i class="material-icons">&#xE872;</i></button>' +
                        '</td>'
                    }
                },
                {
                    orderable: false,
                    className: 'select-checkbox',
                    targets: 7,
                    width: 45
                },
            ],
            'select': {
                'style': 'multi'
            },
            //'order': [[5, 'asc']]
        })

        //Select multil department for deleting
        $('#datatables_department tbody').on( 'click', 'tr', function () {
            let id = parseInt(dataTable.row(this).data()[5])
            arrDepartmentId.push(id)
        });
    }

    //Search icon: filter form department
    $('#icon_serach_department').click(function() {
        let minDate = $('#datetimepicker-date input').val()

        console.log(minDate)

        let maxDate = $('#datetimepicker-date2 input').val()

        console.log(maxDate)

        let type = $('#select_type_depatment').val()

        let inputSearch = $('#search_input_department').val()
        
        if (inputSearch === '') inputSearch = null;

        dataTable.destroy()

        getListDepartments(minDate, maxDate, type, inputSearch, 1, PAGE_SIZE, ORDER_BY);
    })


    $('#btn_close_form_department').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_dismiss_form_department').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_no_department').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_close_delete_department').click(function() {
        dataTable.$('tr.selected').removeClass('selected');
    })

    $('#btn_add_department').click(function(e) {
        flagCreateNewDepartment = true

        $('#department_name').val('')
        $('#department_type').val('')

        $('#div_add_accounts').hide()
    })

    $('#btn_save_department').click(function(e) {
        e.preventDefault()
        let department = {
            name: $('#department_name').val(),
            totalMember: arrAccounts.length,
            type: $('#department_type').val(),
            accounts: arrAccounts,
        }
        let urlAPI = ''
        let methodRequest = ''
        if (flagCreateNewDepartment == true) {
            methodRequest = 'POST'
            urlAPI = 'http://localhost:8888/api/departments/'
        }else {
            urlAPI = 'http://localhost:8888/api/departments/' + departmentId
            methodRequest = 'PUT'
        }
        $.ajax({
            url: urlAPI,
            method: methodRequest,
            contentType: 'application/json',
            dataType: 'json',

            /* Basic authentication */
            // beforeSend: function(xhr) {
            //     xhr.setRequestHeader('Authorization', 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password')))
            // },

            /* JWT authentication */

            data: JSON.stringify(department),

            success: function(data) {
                console.log(data)
                if (data.status == 200) {
                    if (flagCreateNewDepartment == true) {
                        console.log('insert successfully')

                         //Reset flag update
                        flagCreateNewDepartment = false;
                    }else {
                        console.log('update successfully')
                    }

                    clearArrAccounts()
                }

                $('#btn_dismiss_form_department').click()
            },
            error: function(request, status, error) {
                console.log(error)

                clearArrAccounts()
            }

        })

    })

    $('#btn_add_account_department').click(function(e) {
        e.preventDefault()

        $('#modal_add_account_department').modal()

        createTableListAccounts()
        clearArrAccounts()
    })

    $('#btn_close_add_account_department').click(function() {
        dataTableAccount.$('tr.selected').removeClass('selected');
        dataTableAccount.destroy()
        clearArrAccounts()
    })

    //Clear arrAccounts after delete multiple
    const clearArrAccounts = () => {
        while(arrAccounts.length > 0) {
            arrAccounts.pop()
        }
    } 

    //Get list account to add into department
    $('#btn_save_accounts_department').click(function() {

        let accountsSelected = dataTableAccount.rows('.selected').data();

        if (accountsSelected.length > 0) {
            $('#div_add_accounts').show()
            let listUserNameAccounts = ''//List string username
            for (let i = 0; i < accountsSelected.length; i ++) {
                if (i == 0) {
                    listUserNameAccounts = accountsSelected[0][0]
                }else {
                    listUserNameAccounts += ';' + accountsSelected[i][0]
                }

                let account = {
                    id: accountsSelected[i][5],
                    username: accountsSelected[i][0],
                    firstName: accountsSelected[i][1],
                    lastName: accountsSelected[i][2],
                    role: accountsSelected[i][3],
                    password: accountsSelected[i][6]
                }
                arrAccounts.push(account)

            }
            $('#div_add_accounts input').val(listUserNameAccounts)
        }
        dataTableAccount.destroy()
        $('#btn_cancel_accounts_department').click()
    })

    function createTableListAccounts() {
        $.ajax({
            url: 'http://localhost:8888/api/accounts/department-id-null',
            method: 'GET',

            /* Basic authentication */
            // beforeSend: function(xhr) {
            //     xhr.setRequestHeader('Authorization', 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password')))
            // },

            /* JWT authentication */

            success: function(response) {
                let listAccounts = response
                console.log(response)
                let accounts = []
                for (let i = 0; i < listAccounts.length; i ++) {
                    let temp = []
                    temp[0] = listAccounts[i].userName
                    temp[1] = listAccounts[i].firstName
                    temp[2] = listAccounts[i].lastName
                    temp[3] = listAccounts[i].firstName + ' ' + listAccounts[i].lastName
                    temp[4] = listAccounts[i].role
                    //temp[4] = listAccounts[i].departmentName
                    temp[5] = listAccounts[i].id
                    temp[6] = listAccounts[i].password
                    temp[7] = ''
                    accounts.push(temp)
                }
                
                dataTableAccount = $('#datatables_accounts_department').DataTable({
                    data: accounts,
                    "bPaginate": false,
                    "bFilter": false,
                    "bInfo": false,
                    "bSort": false,
                    columns: [
                        { title: "Username"},
                        { title: "First Name"},
                        { title: "Last Name"},
                        { title: "Full Name"},
                        { title: "Role"},
                        { title: ""},
                        { title: ""}
                    ],
                    "columnDefs": [
                        {
                            "targets": 5,
                            "visible": false,
                        },
                        {
                            "targets": 6,
                            "visible": false,
                        },
                        {
                            orderable: false,
                            className: 'select-checkbox',
                            targets: 7,
                            width: 45,
                        },
                    ],
                    'select': {
                        'style': 'multi'
                    },
                })
            },
            error: function(error) {
                console.log(error)
            }
        })

    }

    $('#btn_delete_multi_deparment').click(function() {
        if (arrDepartmentId.length == 0) alert('Mời bạn chọn department cần xóa')
        else {
            $('#defaultModalDanger').modal()

            flagDeleteMultiDepartment = true
        }
    })

    $('#datatables_departments').on('click', 'td .edit', function (e) {
        e.preventDefault()
        flagCreateNewDepartment = false

        console.log('edit on table department...')

        $('#title_form_department').html('Edit Department')

        //Tương tự phần account
    })

    $('#btn_add_department').click(function(e) {
        e.preventDefault()
        $('#title_form_department').html('Create New Department')
    })
    
}