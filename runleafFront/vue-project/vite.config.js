import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'


export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },


  server: {
    host: '192.168.219.111',
    port: 443,
    https: {
      key: 'path/to/private.pem',
      cert: 'path/to/certificate.pem',
      ca: 'path/to/ca_bundle.pem',
    },
  },
  
})
