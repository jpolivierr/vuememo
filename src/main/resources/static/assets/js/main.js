
const getElementById = (element) => {
    return document.querySelector(`#${element}`)
}

const getAllElementsById = (element) => {
    return document.querySelectorAll(`#${element}`)
}

const openSideNavModal = () => {
    const modalOverlay = getElementById("modal-overlay")
    const sideNav = getElementById("side-nav")
    modalOverlay.classList.remove("hide")
    modalOverlay.classList.add("show")
    sideNav.classList.remove("hide")
    sideNav.classList.add("show")
}

const closeSideNavModal = () => {
    const modalOverlay = getElementById("modal-overlay")
    const sideNav = getElementById("side-nav")
    modalOverlay.classList.remove("show")
    modalOverlay.classList.add("hide")
    sideNav.classList.remove("show")
    sideNav.classList.add("hide")
}

const navMenu = getElementById("nav-menu")
const modalOverlay = getElementById("modal-overlay")
const closeSideNav = getElementById("close-side-nav")
const listItemsCollapse = getAllElementsById("list-items-collapse")

console.log(listItemsCollapse)

navMenu.addEventListener('click', () => {
    openSideNavModal()
})

modalOverlay.addEventListener('click', (e) => {
    closeSideNavModal()
})

closeSideNav.addEventListener('click', (e) => {
    closeSideNavModal()
})

listItemsCollapse.forEach(listItem => {

    console.log(listItem)

    listItem.addEventListener('click', (e) => {

        const element = e.currentTarget

        if(e.target.tagName !== 'H5') return
    
        if(!element.classList.contains('expand')){
            element.classList.add("expand")
        }else{
            element.classList.remove("expand")
        }
    })
})