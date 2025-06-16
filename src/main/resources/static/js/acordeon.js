  const toggleBtn = document.getElementById("toggleMenu");
        const crudMenu = document.getElementById("crudMenu");
        const icon = toggleBtn.querySelector("i");

        toggleBtn.addEventListener("click", () => {
            crudMenu.classList.toggle("visible");
            icon.classList.toggle("fa-chevron-right");
            icon.classList.toggle("fa-chevron-left");
        });