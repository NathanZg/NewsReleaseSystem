import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('counter', () => {
    const id = ref(0)
    const name = ref('')
    const password = ref('')
    const role = ref('')
    function setId(userId: number) {
        id.value = userId
    }
    function setName(userName: string) {
        name.value = userName
    }
    function setPassword(userPassword: string) {
        password.value = userPassword
    }
    function setRole(userRole: string) {
        role.value = userRole
    }
    return { id, name, password, role, setId, setName, setPassword, setRole }
}, {
    persist: true
})
