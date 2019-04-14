/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getAccounts(contextpath)
{
    //alert(contextpath);

    var List;
    var i = 0;

    $.ajax({
        type: "GET",
        url: contextpath + "/GetAccounts",
        dataType: "json",
        success: function (data) {
            //alert(data);
            $.each(data.account, function (i, obj)
            {
                //alert(obj.accountID);
                var div_data = "<option value=" + obj.accountID + ">" + obj.accountID + "</option>";
                // alert(div_data);
                $(div_data).appendTo('#accountID');
            });
        }
//            List = data.aaData;
//            $('#accountID').empty();
//            $('#accountID').append('<option value="">All</option>');
//            for (i in List) {
//                $('#accountID').append('<option value="' + List[i].accountID + '">' + List[i].accountID + '</option>');
//            }
//        }

    });

}


function getGroups(contextpath, accountID)
{
   
   // alert(contextpath + "," + accountID + "," + groups);



    $.ajax({
        type: "GET",
        url: contextpath + "/GetGroups?accountID=" + accountID,

        dataType: "json",
        success: function (data) {
            // alert(data);
            var select = $('#groupID');
            
            select.find('option').remove();
            $.each(data.group, function (i, obj)
            {
                //alert(obj.accountID);
                var div_data = "<option value=" + obj.groupID + ">" + obj.groupID + "</option>";
                // alert(div_data);
                $(div_data).appendTo('#groupID');
            });

        }
//            List = data.aaData;
//            $('#accountID').empty();
//            $('#accountID').append('<option value="">All</option>');
//            for (i in List) {
//                $('#accountID').append('<option value="' + List[i].accountID + '">' + List[i].accountID + '</option>');
//            }
//        }

    });

}