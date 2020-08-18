<template>
  <div class="inyectamensajesvalidacionenlayout">
    <input type="hidden" id="nombreObligatorio" :value="$t('validaciones.nombreObligatorio')" />
    <input type="hidden" id="nombreMin10Caracteres" :value="$t('validaciones.nombreMin10Caracteres')" />
    <input type="hidden" id="mailObligatorio" :value="$t('validaciones.mailObligatorio')" />
    <input type="hidden" id="obligatorio" :value="$t('validaciones.obligatorio')" />
    <input type="hidden" id="mailValido" :value="$t('validaciones.mailValido')" />
    <input type="hidden" id="passMin10" :value="$t('validaciones.passMin10')" />

    <input type="hidden" id="casosDia" :value="$t('mapa.casosDia')" />
    <input type="hidden" id="muertesDia" :value="$t('mapa.muertesDia')" />
    <input type="hidden" id="muertesAcumuladas" :value="$t('mapa.muertesAcumuladas')" />
    <input type="hidden" id="casosAcumulados" :value="$t('mapa.casosAcumulados')" />


    <input type="hidden" id="exito" :value="$t('shared.exito')" />
    <input type="hidden" id="exitoAutenticado" :value="$t('shared.exitoAutenticado')" />
    <input type="hidden" id="error" :value="$t('shared.error')" />
    <input type="hidden" id="errorRevisa" :value="$t('shared.errorRevisa')" />
  </div>
</template>
<script>
export default {
  data: () => ({
    loading: false,
  }),
  methods: {
    start() {
      this.loading = true;
    },
    finish() {
      this.loading = false;
    },
  },
};
function getValidacionTraduccion(id) {
  var el = document.getElementById(id);
  if (el != undefined && el != null) {
    return el.value;
  }
}
</script>