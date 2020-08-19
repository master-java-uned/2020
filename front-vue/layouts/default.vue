<template>
  <v-app light>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js" />
    <script src="https://cdn.jsdelivr.net/npm/stomp-websocket-js@1.0.0/lib/stomp.js" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet" />
    <ValMessage></ValMessage>
    <menuFront v-if="esFront" />
    <menuBack v-else />

    <v-main class="pb-0">
      <nuxt class="pa-0 ma-0"/>
    </v-main>
    <v-navigation-drawer v-model="rightDrawer" :right="right" temporary fixed>
      <v-list>
        <v-list-item @click.native="right = !right">
          <v-list-item-action>
            <v-icon light>mdi-repeat</v-icon>
          </v-list-item-action>
          <v-list-item-title>Switch drawer (click me)</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-footer
      :absolute="false"
    >
      <v-container class="pa-0 ma-0 mt-5">
        <div class="row mt-0 pa-0 ma-0" style="width: 100vw;">
          <div class="pa-0 ma-0 col-md-6 col-lg-8 mx-auto text-center">
            <v-icon size="50">mdi-facebook</v-icon>
            <v-icon size="50">mdi-twitter</v-icon>
            <v-icon size="50">mdi-instagram</v-icon>
            <v-icon size="50">mdi-linkedin</v-icon>

            <p class="pa-0 ma-0 copyright text-muted text-center<" style="max-width: 100vw;">
              Copyright © UNED 2020
              <br />Desarrollado por Víctor y Peter Fight
            </p>
          </div>
        </div>
      </v-container>
    </v-footer>
  </v-app>
</template>

<script>
import ValMessage from "~/components/nyapaMensajesValidacion.vue";
import ModeloDatosCovid from "~/app_code/ModeloDatosCovid";

import menuFront from "~/components/menuFront.vue";
import menuBack from "~/components/menuBack.vue";

export default {
  components: {
    ValMessage,
    menuFront,
    menuBack,
  },
  data() {
    return {
      esFront: true,
      clipped: false,
      drawer: false,
      fixed: false,
      items: [
        {
          icon: "mdi-apps",
          title: "Welcome",
          to: "/",
        },
        {
          icon: "mdi-chart-bubble",
          title: "Inspire",
          to: "/inspire",
        },
      ],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: "COVID-19",
    };
  },
  mounted: function () {
    if (
      sessionStorage.getItem("rolId") != undefined &&
      sessionStorage.getItem("rolId") != "" &&
      sessionStorage.getItem("rolId") != 1
    ) {
      this.esFront = false;
    }
    let ws = new SockJS("http://localhost:8081/websocket");
    this.client = Stomp.over(ws);
    let that = this;
    that.client.connect({}, function (frame) {
      that.client.subscribe("/topic/updateData", (message) => {
        console.log(message.body);
        if (message.body) {
          this.message = message.body;
          ModeloDatosCovid.init(this.message);
        }
      });
    });
  },
};
</script>
<style>
footer * {
  color: #344e5e !important;
  background: white !important;
  box-shadow: none !important;
}

.v-footer {
  margin: 0 !important;
  padding: 0 !important;
  width: 100vw !important;
  background: white !important;
}

.cabecera {
  color: #344e5e !important;
}

html * {
  font-family: "Montserrat", sans-serif;
}

.swal2-shown * {
  /* font-family: montserrat; */
  font-family: "Montserrat", sans-serif;
}

header {
  width: 100vw;
}
</style>
