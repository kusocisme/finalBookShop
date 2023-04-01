
document.addEventListener('DOMContentLoaded', async () => {

    const command = document.getElementById("command").value
    if (command === "ADD") {
        return;
    }

    const response = await fetch('../admin/uploadKey?type=file');
    const data = await response.json();

    const options = {
        cloudName: data.cloudname,
        apiKey: data.apikey,
        uploadSignatureTimestamp: data.timestamp,
        uploadSignature: data.signature,
        cropping: false,
        folder: data.folder
    }


    const processResults = (error, result) => {
        if (!error && result && result.event === 'success') {
            let photoInfo = [];
            photoInfo.push("command" + "=" + 'Update');
            photoInfo.push("url" + "=" + result.info.url);
            photoInfo.push("publicId" + "=" + result.info.public_id);
            photoInfo.push("productId" + "=" + document.getElementById("productId").value);
            photoInfo = photoInfo.join("&");
            fetch("file", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                redirect: 'follow',
                body: photoInfo
            }).then(() => {
                location.reload()
            });
        }
    }

    const myWidget = window.cloudinary.createUploadWidget(
        options,
        processResults
    )
    document
        .getElementById('uploadFile')
        .addEventListener('click', () => {
            myWidget.open();
        }, false)
})
