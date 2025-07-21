export function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove("hidden");
    } else {
        console.warn(`Modal with ID "${modalId}" not found`);
    }
}

export function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add("hidden");
    } else {
        console.warn(`Modal with ID "${modalId}" not found`);
    }
}
