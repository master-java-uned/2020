<template>
  <v-img
    class="ma-0 pa-0 mt-0"
    style="width: 100vw; height: 50vh;"
    :src="require('~/assets/img/nuclear.jpg')"
  >
    <v-row align="center" justify="center" width="100" class="w-100" style="margin-top: 5%;">
      <v-card
        class="text-center pa-5"
        cols="12"
        rounded
        style="background: rgba(255, 255, 255, 0.5);"
      >
        <v-card-title>
          <h1 v-if="error.statusCode === 404">{{ pageNotFound }}</h1>
          <h1 v-else>{{ otherError }}</h1>
        </v-card-title>
        <v-card-subtitle>
          <h4 class="subheading">{{ $t("index.subheading1") }}</h4>
        </v-card-subtitle>
      </v-card>
    </v-row>
  </v-img>
</template>

<script>
export default {
  layout: "empty",
  props: {
    error: {
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      pageNotFound: "404 Not Found",
      otherError: "An error occurred",
    };
  },
  head() {
    const title =
      this.error.statusCode === 404 ? this.pageNotFound : this.otherError;
    return {
      title,
    };
  },
};
</script>

<style scoped>
h1 {
  font-size: 20px;
}
</style>
