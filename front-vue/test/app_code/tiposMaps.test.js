import { mount } from '@vue/test-utils'
import tiposMaps from '@/app_code/maps/tiposMaps'

describe('tiposMaps', () => {
  test('Instancia creada', () => {
    expect(tiposMaps.CASOS_ACUMULADOS_X_FECHA == 3).toBeTruthy();
  })
})