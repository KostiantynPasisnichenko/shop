function toggleDropdown() {
    const menu = document.getElementById('dropdownMenu');
    menu.style.display = (menu.style.display === 'block') ? 'none' : 'block';
}

document.addEventListener('click', function(event) {
    const menu = document.getElementById('dropdownMenu');
    const usernameLink = document.getElementById('username');

    if (!usernameLink.contains(event.target) && !menu.contains(event.target)) {
        menu.style.display = 'none';
    }
});
