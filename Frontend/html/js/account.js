// $(document).ready(function () {

    function loadAccount() {
        console.log('account page js...')
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
                    console.log(response)
                    $.each(response, function(index, option) {   
                        $('#select_department_account')
                            .append($("<option></option>")
                                    .attr("value", option.id)
                                    .text(option.name));
                        
                        $('#account_department')
                            .append($("<option></option>")
                            .attr("value", option.id)
                            .text(option.name));
                });
                },
                error: function(error) {
                    console.log(error)
                }
            })
        }

        getListDepartments()
        
        function getListAccounts(roleInput, departmentIdInput, searchInput, pageNumberInput, pageSizeInput, orderByInput) {
            let url = "http://localhost:8888/api/accounts/paging"
            $.ajax({
                url: url,
                type: 'GET',
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
                    console.log(response)
                    createTableAccount(response.content)
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
                temp[0] = listAccounts[i].firstName + " " + listAccounts[i].lastName
                temp[1] = listAccounts[i].username
                temp[2] = listAccounts[i].role
                temp[3] = listAccounts[i].email
                //let departmentName = listAccounts[i].departments.reduce(function (dpName, obj) { return (dpName + ';' + obj.name) }, '')
                temp[4] = listAccounts[i].departmentName
                temp[5] = listAccounts[i].departmentId;
                temp[6] = ''
                temp[7] = ''
                accounts.push(temp)
            }

            dataTable = $('#datatables_accounts').DataTable({
                data: accounts,
                columns: [
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
                        "targets": 5,
                        "visible": false
                    },
                    {
                        "targets": 6,
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
                        targets: 7,
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

        
        $('#icon_search_account').on('click', function() {
            alert('123abc')
            let role = $('#select_role_account').val()
            let departmentId = $('#select_department_account').val()
            let inputSearch = $('#search_input_account').val()
        
            if (departmentId == null) departmentId = 0;
            if (inputSearch === '') inputSearch = null;
        
            dataTable.destroy()
        
            getListAccounts(role, departmentId, inputSearch, 1, PAGE_SIZE, ORDER_BY);
        })

        $('#btn_delete_multil_account').click(function() {
            alert('123abc')
        })
        
        document.getElementById("icon_search_account").onclick = function() {
            alert('123abc')
        };

        document.getElementById("icon_search_account").addEventListener("click", function (){
            
            console.log('123abc')
        });

    }

//})

