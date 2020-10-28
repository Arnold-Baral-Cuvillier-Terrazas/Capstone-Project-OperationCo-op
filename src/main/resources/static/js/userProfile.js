
//
// //starting functionality for profile page
//
// // const options = {
// //     onFileUploadFinished: file => {
// //         // If you throw any error in this function it will reject the file selection.
// //         // The error message will be displayed to the user as an alert.
// //         if (file.size > 1000 * 1000) {
// //             throw new Error('File too big, select something smaller than 1MB');
// //         }
// //         // console.log(file.url);
// //
// //         //fetch request
// //         fetch(url , {
// //             method: 'POST',
// //             headers: {
// //                 'Content-Type': 'application/x-www-form-urlencoded',
// //             },
// //             body: $.param({userId: userId, url: file.url})
// //         });
// //     }
// // };
//
const options = {
    onUploadDone : updateImage ,
    accept: 'image/*',
    maxSize: 10* 1000* 1000,
    uploadInBackground: false
}

function updateImage(result){
    const filedata = result.filesUploaded[0];
    console.log(filedata);

    $("#profileUrl").val(filedata.url);

}

// client.picker().open();


$(document).ready(function() {

    //filestack
    console.log(url);
    $("#profile-btn").click(function(){
        stackClient.picker(options).open();
    });



})


//testing merge conflict

//starting functionality for profile page


const options = {
    onUploadDone: updateImage,
    accept: 'image/*',
    maxSize: 10 * 1000 * 1000,
    uploadInBackground: false
}

function updateImage(result) {
    const filedata = result.filesUploaded[0];
    console.log(filedata);
    $("#profileUrl").val(filedata.url);
}

//jquery functcionality
$(document).ready(function () {

    //filestack
    console.log(url);
    $("#profile-btn").click(function () {
        stackClient.picker(options).open();
    });

})

