document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();

    var form = event.target;
    var formData = new FormData(form);

    fetch("/register", {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            form.reset();
        })
        .catch(error => console.error("Error:", error));
});