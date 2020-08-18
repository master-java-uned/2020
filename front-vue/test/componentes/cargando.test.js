import { mount } from '@vue/test-utils'
import cargando from '@/components/cargando.vue'

describe('cargando', () => {
  test('Es instancia VUE', () => {
    const wrapper = mount(cargando)
    expect(wrapper.vm).toBeTruthy()
  })
})