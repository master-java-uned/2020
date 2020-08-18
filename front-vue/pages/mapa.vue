<template>
  <v-flex>
    <link rel="stylesheet" type="text/css" :href="require('~/static/jvectormap.min.css')" />
    <v-sheet v-if="!mapaCargado" class="pa-0 ma-0 grey lighten-4">
      <v-progress-linear color="green accent-4" indeterminate rounded height="6"></v-progress-linear>
      <v-sheet color="grey lighten-4" class="px-3 pb-10">
        <v-skeleton-loader class="mx-auto my-5" max-width="300" type="heading"></v-skeleton-loader>
        <v-skeleton-loader class="mx-auto my-5" max-width="300" type="list-item"></v-skeleton-loader>
        <v-skeleton-loader class="mx-auto" max-width="300" type="list-item"></v-skeleton-loader>
        <v-skeleton-loader class="mx-auto" max-width="300" type="list-item-avatar"></v-skeleton-loader>
        <br />
        <v-skeleton-loader class="mx-auto" max-width="300" type="list-item"></v-skeleton-loader>
        <br />
        <v-skeleton-loader class="mx-auto" max-width="300" type="image"></v-skeleton-loader>
      </v-sheet>
      <v-progress-circular
        :width="3"
        color="green"
        indeterminate
        style="height: 100px; width: 100px; position: absolute; top: 25%; left: calc(50% - 50px);"
      ></v-progress-circular>
    </v-sheet>
    <v-sheet :class="mapaCargado? '': 'd-none'">
      <v-row class="col-12">
        <div class="col-md-3"></div>
        <div class="col-md-6">
          <v-card elevation="12" class="pa-5 pb-10 d-block w-100" style="width: 100%;">
            <v-card-title>{{ $t("mapa.mapa") }}</v-card-title>
            <v-card-subtitle>{{ $t("mapa.mapaSubtitle") }}</v-card-subtitle>
            <v-card-text>
              <div class></div>
              <v-select
                background-color="white !important"
                v-model="select"
                :items="items"
                item-text="texto"
                item-value="cod"
                label="Select"
                persistent-hint
                return-object
                single-line
                id="selectorTipoMapa"
                @change="tipoMapaChanged($event)"
                append-icon="mdi-plus"
                outlined
              ></v-select>
              <div class></div>

              <div class="text-center mb-10">
                <v-btn color="primary d-inline" v-on:click="mostrarEvolucion">Mostrar evolución</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </div>
        <div class="col-md-3"></div>
      </v-row>

      <v-row class="col-12 pt-10 mt-0" style="background: #344e5e;">
        <v-container>
          <div class>
            <v-slider
              id="sliderFecha($event)"
              v-model="slider"
              @change="updateFecha"
              thumb-label="always"
              value="1"
              :max="arrayFechas.length"
            >
              <template
                v-slot:thumb-label="{ value }"
              >{{arrayFechas.length > 0 ? arrayFechas[parseInt(value) -1] : ''}}</template>
            </v-slider>
          </div>
          <div id="world-map" class="d-flex"></div>
        </v-container>
      </v-row>
    </v-sheet>
    <div :html="arrayFechas"></div>
  </v-flex>
</template>


<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";
import tiposMaps from "~/app_code/maps/tiposMaps";
import ArrayPaisesFactory from "~/app_code/maps/ArrayPaisesFactory";
import ModeloDatosCovid from "~/app_code/ModeloDatosCovid";

export default {
  components: {
    Logo,
    VuetifyLogo,
  },
  methods: {
    tipoMapaChanged(valor) {
      this.tipoMapa = parseInt(valor.cod);
      this.updateArrays(this.textoFechas, true);
    },
    dameArrayFechas() {
      var arrayDistinctFechas = [];
      if (
        ModeloDatosCovid.distinctPaises != undefined &&
        ModeloDatosCovid.distinctPaises != null
      ) {
        for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
          var pais = ModeloDatosCovid.distinctPaises[i];
          pais.arrayInfoDias.forEach(function (value) {
            if (arrayDistinctFechas.indexOf(value.dia) === -1) {
              arrayDistinctFechas.push(value.dia);
            }
          });
        }
      }
      // debugger;
      return arrayDistinctFechas;
    },
    updateArrays(fechaActual, muestraTooltips) {
      this.textoFechas = fechaActual;
      this.Mapa = ArrayPaisesFactory.getTipoFactory(this.tipoMapa, fechaActual);
      setTimeout(() => {
        //si no le meto el delay, no ha cargado el dom (contenedor del mapa) y salta una excepción
        this.Mapa.pintaMapa(muestraTooltips);
      }, 50);
    },
    animacionFechas(fechas, indice) {
      indice = indice + 1;
      if (indice < this.arrayFechas.length) {
        let wait = fechas[indice];
        this.textoFechas = wait;
        this.updateArrays(wait, false);
        this.slider = indice;
        setTimeout(() => {
          this.animacionFechas(fechas, indice);
        }, 50);
      }
    },
    mostrarEvolucion() {
      // debugger;
      this.textoFechas = this.arrayFechas[1];
      this.updateArrays(this.arrayFechas[1], false);
      var internalArrayFechas = this.arrayFechas;
      this.animacionFechas(internalArrayFechas, 1); //Si no voy borrando las fechas buenas
    },
    updateFecha(value) {
      this.textoFechas = this.arrayFechas[parseInt(value)];
      this.updateArrays(this.arrayFechas[parseInt(value)], true);
    },
    firstLoad() {
      this.arrayFechas = this.dameArrayFechas();
      if (
        !this.mapaCargado &&
        this.arrayFechas != undefined &&
        this.arrayFechas != null &&
        this.arrayFechas.length > 0
      ) {
        this.mapaCargado = true;
        this.arrayFechas = this.arrayFechas.sort(function (a, b) {
          var aa = a.split("/").reverse().join(),
            bb = b.split("/").reverse().join();
          return aa < bb ? -1 : aa > bb ? 1 : 0;
        });
        this.slider = this.arrayFechas.length;
        var fechaActual = this.arrayFechas[this.arrayFechas.length - 1];
        this.tipoMapa = tiposMaps.CASOS_X_FECHA;
        this.updateArrays(fechaActual, true);
        // this.updateFecha(1);
      } else {
        //Sigue jugando
        setTimeout(() => {
          this.firstLoad();
        }, 2000);
      }
    },
  },
  data() {
    return {
      mapaCargado: false,
      slider: 1,
      arrayFechas: [],
      tipoMapa: "",
      textoFechas: "",
      continente: "",
      select: { texto: "Casos por día", cod: "1" },
      items: [
        { texto: document.getElementById("casosDia").value, cod: "1" },
        { texto: document.getElementById("muertesDia").value, cod: "0" },
        { texto: document.getElementById("muertesAcumuladas").value, cod: "2" },
        { texto: document.getElementById("casosAcumulados").value, cod: "3" },
      ],
    };
  },
  mounted: function () {
    this.firstLoad();
  },
};
</script>
<style scoped>
.v-card {
  background: #344e5e;
  padding: 2em;
  height: 325px;
}

.v-card > * {
  color: white !important;
}

.rounded-circle {
  border-radius: 50% !important;
  border: 1px solid black;
  box-shadow: 3px 3px 30px black;
}

svg {
  height: 500px !important;
}
</style>