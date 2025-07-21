import { getAllDoctors, addDoctor } from './doctorService.js';
import { createDoctorCard } from './doctorCard.js';
import { openModal, closeModal } from './modal.js';

document.addEventListener("DOMContentLoaded", async () => {
    const doctorList = document.getElementById("doctorList");
    const searchInput = document.getElementById("searchDoctorByName");
    const specialtyFilter = document.getElementById("filterBySpecialty");
    const timeFilter = document.getElementById("filterByTime");

    let doctors = [];

    // Doktorları çek ve göster
    async function loadDoctors() {
        doctors = await getAllDoctors();
        renderDoctors(doctors);
    }

    // Kartları göster
    function renderDoctors(filtered) {
        doctorList.innerHTML = "";
        filtered.forEach(doctor => {
            const card = createDoctorCard(doctor);
            doctorList.appendChild(card);
        });
    }

    // Arama ve filtreleme
    function applyFilters() {
        const nameQuery = searchInput.value.toLowerCase();
        const selectedSpecialty = specialtyFilter.value;
        const selectedTime = timeFilter.value;

        const filtered = doctors.filter(doctor => {
            const nameMatch = doctor.first_name.toLowerCase().includes(nameQuery) || doctor.lastname.toLowerCase().includes(nameQuery);
            const specialtyMatch = selectedSpecialty === "" || doctor.specialization === selectedSpecialty;
            const timeMatch = selectedTime === "" || doctor.availableTimes?.includes(selectedTime);
            return nameMatch && specialtyMatch && timeMatch;
        });

        renderDoctors(filtered);
    }

    // Event listener'lar
    searchInput.addEventListener("input", applyFilters);
    specialtyFilter.addEventListener("change", applyFilters);
    timeFilter.addEventListener("change", applyFilters);

    // Modal aç/kapat
    document.getElementById("openAddDoctorModal").addEventListener("click", () => openModal("addDoctorModal"));
    document.getElementById("closeAddDoctorModal").addEventListener("click", () => closeModal("addDoctorModal"));

    // Doktor ekleme formu
    document.getElementById("addDoctorForm").addEventListener("submit", async (e) => {
        e.preventDefault();

        const newDoctor = {
            first_name: document.getElementById("newDoctorFirstName").value,
            lastname: document.getElementById("newDoctorLastName").value,
            specialization: document.getElementById("newDoctorSpecialty").value,
            email: document.getElementById("newDoctorEmail").value,
            phone: document.getElementById("newDoctorPhone").value,
            username: document.getElementById("newDoctorUsername").value,
            password: document.getElementById("newDoctorPassword").value
        };

        await addDoctor(newDoctor);
        closeModal("addDoctorModal");
        loadDoctors(); // Listeyi güncelle
        e.target.reset();
    });

    await loadDoctors(); // Sayfa açıldığında doktorları getir
});
