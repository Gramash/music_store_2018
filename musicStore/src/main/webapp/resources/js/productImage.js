var modal = document.getElementById('myModal');

function zoomImage(image, name){
        
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");

    modal.style.display = "block";
    modalImg.src = image.src;
    captionText.innerHTML = image.alt;
    

}

function hide(){
    var span = document.getElementsByClassName("close")[0];
    modal.style.display = "none";
}



