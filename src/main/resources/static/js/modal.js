"use stcrict";

const refs = {
    openModalBtn:document.querySelector("[data-modal-open]"),
    modal:document.querySelector("[data-modal]"),
    closeModalBtn:document.querySelector("[data-modal-close]"),
};

const toggleModal = () => {
    refs.modal.classList.toggle("is-hidden");
    document.body.classList.toggle("no-scroll");
}

refs.openModalBtn.addEventListener("click", toggleModal);
refs.closeModalBtn.addEventListener("click", toggleModal);  


