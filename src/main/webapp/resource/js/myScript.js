
function exportTb(filename){
    $("#keywords").table2excel({
        exclude: ".noExl",
        name: "Excel Document Name",
        filename: filename,
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
    });
}

function getDays(dateStart,dateEnd) {
    tablebody.find("tr").remove();
    $.get(
        "../excitation",
        {
            "doWhat": "daysCount",
            "startDate": dateStart,
            "endDate": dateEnd,
        },
        function (data) {
            for (let i in data){
                tablebody.append(
                    '<tr>' +
                    '<td>'+ data[i].name +'</td>' +
                    '<td>'+ data[i].receptionCount +'</td>' +
                    '<td>'+ data[i].fiveStarCount +'</td>' +
                    '<td>'+ data[i].fourStarCount +'</td>' +
                    '<td>'+ data[i].threeStarCount +'</td>' +
                    '<td>'+ data[i].twoStarCount +'</td>' +
                    '<td>'+ data[i].oneStarCount +'</td>' +
                    '</tr>'
                )
            }
            $("table").trigger("update",[true])
        },
        "json"
    )
}
function getDaily(dateStart) {
    tablebody.find("tr").remove();
    $.get(
        "../excitation",
        {
            "doWhat": "dailyCount",
            "date": dateStart,
        },
        function (data) {
            for (let record of data){
                tablebody.append(
                    '<tr>' +
                    '<td>'+ record.name +'</td>' +
                    '<td>'+ record.scenicName +'</td>' +
                    '<td>'+ record.receptionCount +'</td>' +
                    '<td>'+ record.fiveStarCount +'</td>' +
                    '<td>'+ record.fourStarCount +'</td>' +
                    '<td>'+ record.threeStarCount +'</td>' +
                    '<td>'+ record.twoStarCount +'</td>' +
                    '<td>'+ record.oneStarCount +'</td>' +
                    '</tr>'
                )
            }
            $("table").trigger("update",[true]);
        },
        "json"
    )
}

function getSinglePerson(dateStart,endStart,employeeId) {
    tablebody.find("tr").remove();
    $.get(
        "../excitation",
        {
            "startDate": dateStart,
            "endDate": endStart,
            "employeeId": employeeId,
        },
        function (data) {
            for (let record of data){
                tablebody.append(
                    '<tr>' +
                    '<td>'+ record.name +'</td>' +
                    '<td>'+ record.timeDate +'</td>' +
                    '<td>'+ record.scenicName +'</td>' +
                    '<td>'+ record.receptionCount +'</td>' +
                    '<td>'+ record.fiveStarCount +'</td>' +
                    '<td>'+ record.fourStarCount +'</td>' +
                    '<td>'+ darecordta.threeStarCount +'</td>' +
                    '<td>'+ record.twoStarCount +'</td>' +
                    '<td>'+ record.oneStarCount +'</td>' +
                    '</tr>'
                )
            }
            $("table").trigger("update",[true])
        },
        "json"
    )
}
function getEmpInfo(type) {
    type=type==null?'':type;
    tablebody.find("tr").remove();
    $.get(
        "../adminServlet",
        {
            "doWhat": "getEmp",
            "type": type,
        },
        function (data) {
            if (type=='onjob'){
                loadOnJob(data);
            }else {
                loadNotOnJob(data);
            }
        },
        "json"
    )
}
function loadOnJob(data) {
    let i=1;
    for (let emp of data){
        tablebody.append(
            '<tr>' +
            '<td>'+i+'</td>' +
            '<td>'+emp.name+'</td>' +
            '<td class="empid">'+emp.employeeId+'</td>' +
            '<td>'+emp.gender+'</td>' +
            '<td>'+emp.age+'</td>' +
            '<td class="empStatus">'+emp.status+'</td>' +
            '<td><button onclick="changeEmpStatus(this,1)" class="changeemp">注销</button></td>' +
            '</tr>'
        );
        i++;
    }
    $("table").trigger("update",[true]);
}

function loadNotOnJob(data) {
    let i=1;
    for (let emp of data){
        tablebody.append(
            '<tr>' +
            '<td>'+i+'</td>' +
            '<td>'+emp.name+'</td>' +
            '<td class="empid">'+emp.employeeId+'</td>' +
            '<td>'+emp.gender+'</td>' +
            '<td>'+emp.age+'</td>' +
            '<td>'+emp.status+'</td>' +
            '<td><button onclick="changeEmpStatus(this,2)" class="changeemp">办理入职</button>' +
            '<button onclick="changeEmpStatus(this,3)" class="changeemp">删除</button></td>' +
            '</tr>'
        );
        i++;
    }
    $("table").trigger("update",[true]);

}
function changeEmpStatus(me,action) {
    let act= "";
    switch (action) {
        case 1: act="writeOff";break;
        case 2: act="pass";break;
        case 3: act="delete";break;
    }
    $.get(
        "../adminServlet",
        {
            "doWhat": act,
            "empID": $(me).parent().parent().find(".empid").text(),
        },
        function (data) {
            if (data.result=="success"){
                $(me).parent().parent().remove();
            }
        },
        "json"
    )
}
function getScenicDaily(dateStart) {
    tablebody.find("tr").remove();
    $.get(
        "../excitation",
        {
            "doWhat": "scenicDailyCount",
            "dateTime": dateStart,
        },
        function (data) {
            loadScenicReport(data)
        },
        "json"
    )
}
function getScenicDays(dateStart,dateEnd){
    tablebody.find("tr").remove()
    $.post(
        "../excitation",
        {
            "doWhat":"daysScenicCount",
            "startDate":dateStart,
            "endDate":dateEnd,
        },
        function (data){
            loadScenicReport(data)
        },
        "json"
    )
}
function getSingleScenic(dateStart,dateEnd,scenic){
    tablebody.find("tr").remove()
    $.post(
        "../excitation",
        {
            "doWhat":"singleScenicCount",
            "startDate":dateStart,
            "endDate":dateEnd,
            "scenic":scenic,
        },
        function (data){
            let i=0;
            for(let record of data){
                i+=1;
                tableBody.append(
                    '<tr>' +
                    '<td>'+i+'</td>' +
                    '<td>'+scenic+'</td>' +
                    '<td>'+record.timeDate+'</td>' +
                    '<td>'+record.count+'</td>' +
                    '</tr>'
                )
            }
            $("table").trigger("update", [true]);
        },
        "json"
    )
}
function loadScenicReport(data){
    let i=0
    for(let record of data){
        i+=1;
        tableBody.append(
            '<tr>' +
            '<td>'+i+'</td>' +
            '<td>'+record.scenicName+'</td>' +
            '<td>'+record.count+'</td>' +
            '</tr>'
        )
    }
    $("table").trigger("update", [true]);
}