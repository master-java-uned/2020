/**
 * NOTA PARA NUESTROS YO FUTUROS... TODO LO QUE HAYA QUE TESTAR MEJOR METERLO EN COMPONENTES...
 * A NO SER QUE SE DISPONGA DE TODO EL TIEMPO DEL MUNDO PARA SABER CÓMO PASAR LOS TESTS DE LAS PAGES
 * MEDIANTE VUETIFY, NUXT Y JEST...
 */


// const { resolve } = require('path')
// const { Nuxt, Builder } = require('nuxt')

// // We keep the nuxt and server instance
// // So we can close them at the end of the test
// let nuxt = null

// // Init Nuxt.js and create a server listening on localhost:4000
// beforeAll(async () => {
//   const config = {
//     dev: process.env.NODE_ENV === 'production',
//     rootDir: resolve(__dirname, '../../'),
//     mode: 'universal',
//   }

//   nuxt = new Nuxt(config)

//   await new Builder(nuxt).build()

//   await nuxt.server.listen(3000, 'localhost')
// }, 120000)

// // Example of testing only generated html
// describe('GET /login', () => {
//   test('Route / exits and render HTML', async () => {
//      expect(html).not.toBeNull()
//   })
// })


// // Close server and ask nuxt to stop listening to file changes
// afterAll(() => {
//   nuxt.close()
// })