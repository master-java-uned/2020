<template>
  <v-row
    :style="'padding: 50px 0 50px 0; height: 100; width: 100vw; background-image: url('+require('~/assets/img/COVID.jpg')+');'"
  >
    <!-- <v-col class="col-3 col"></v-col> -->
        <v-spacer></v-spacer>
    <v-col class="col-12 col-md-6 col">
      <v-spacer></v-spacer>
      <v-card elevation="12" class="mx-1" style="background: #344e5e; color: white;">
        <v-spacer></v-spacer>
        <v-card-title>
          <div class="display-1 my-2">Listado de usuarios</div>
        </v-card-title>
        <v-spacer></v-spacer>
        <v-card-text>
          <v-data-table
            :headers="headers"
            :items="users"
            :rows-per-page-items="listSize"
            :pagination.sync="pagination"
            :total-items="totalNumberOfItems"
            @update:pagination="paginate"
            class="elevation-1"
            hide-default-footer
          >
            <!-- <template v-slot:items="props">
              <td>
                <img :src="props.item.imageBase64" />
              </td>
              <td class="text-xs-right">{{ props.firstName }}</td>
              <td class="text-xs-right">{{ props.lastName }}</td>
              <td class="text-xs-right">{{ props.item.email }}</td>
            </template>-->
          </v-data-table>
          <div class="text-center pt-2" style="color: white;">
            <v-pagination
              v-model="indexActual"
              :length="Math.ceil(totalElements / pageSize)"
              @next="next"
              @prev="previo"
              @input="setPage"
            ></v-pagination>

            <p>Total elementos: {{totalElements}}</p>
            <div>
              <div class="row">
                <div class="col-6">Tamaño de la página:</div>
                <div class="col-6">
                  <v-text-field
                    :value="pageSize"
                    label="Elementos por página"
                    type="number"
                    min="1"
                    max="10"
                    @change="updateTabla"
                    background-color="white"
                    style="display: inline; width: 50%; text-align: center !important;"
                    @input="pageSize = parseInt($event)"
                  ></v-text-field>
                </div>
              </div>
              <!-- {{pageSize}} -->
            </div>
            <!-- <p>Pägina actual: {{indexActual}}</p> -->
            <!-- <v-select :items="items" label="Standard"></v-select> -->
          </div>
        </v-card-text>
      </v-card>
    </v-col>
    
        <v-spacer></v-spacer>
  </v-row>
</template>

<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";
import { alertas } from "~/app_code/alertas";

const $ = require("jquery");
export default {
  components: {
    Logo,
    VuetifyLogo,
  },
  mounted: function () {
    this.updateTabla();
  },
  data() {
    return {
      pageSize: 5,
      indexActual: 1,
      users: [],
      totalPages: 0,
      totalPagesArray: [],
      totalElements: 0,
      headers: [
        // {
        //   text: "Imagen",
        //   value: "imageBase64",
        // },
        {
          text: "Nombre",
          value: "firstName",
        },
        {
          text: "Apellidos",
          value: "lastName",
        },
        {
          text: "Mail",
          value: "email",
        },
      ],
      totalNumberOfItems: 0,
      domains: [],
      listSize: [10, 25, 50, 100],
      pagination: {},
    };
  },
  methods: {
    setPageSize: function (e) {
      this.pageSize = evento.target.value;
      this.updateTabla();
      return false;
    },
    setPage(pagina) {
      this.indexActual = pagina;
      this.updateTabla();
      return false;
    },
    previo() {
      if (this.indexActual > 0) {
        this.indexActual--;
        this.updateTabla();
      }
      return false;
    },
    next() {
      if (this.indexActual < this.totalPages) {
        this.indexActual++;
        this.updateTabla();
      }
      return false;
    },
    fetchData(route) {
      debugger;
      this.$ajax({
        method: "GET",
        url:
          "http://127.0.0.1:8090/users/getUsersPaged?pageSize=" +
          paginate.rowsPerPage +
          "&indicePage=" +
          this.pagination.page -
          1,
        okay: (data) => {
          debugger;
          this.totalNumberOfItems = data.resulttotals;
          this.domains = data.items;
        },
        fail: (stat, error) => {
          debugger;
          this.$root.showFailed(error);
        },
      });
    },
    getUsersPaged(pageSize, indicePage) {
      return new Promise(function (resolve, reject) {
        $.ajax({
          type: "GET",
          url:
            "http://127.0.0.1:8090/users/getUsersPaged?pageSize=" +
            pageSize +
            "&indicePage=" +
            indicePage,
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            Authorization: sessionStorage.getItem("token"),
          },
          success: function (result) {
            resolve(result);
          },
          error: function (e) {
            alertas.error("Error", "Se ha producido un error inesperado");
          },
        });
      });
    },
    inicioPaginaActual() {
      return (
        parseInt(this.indexActual.toString()) *
        parseInt(this.pageSize.toString())
      );
    },
    finPaginaActual() {
      return this.inicioPaginaActual() + parseInt(this.pageSize.toString());
    },
    updateTabla() {
      var ctx = this;
      this.pageSize = this.pageSize > 10? 10 : parseInt(this.pageSize);

      this.getUsersPaged(this.pageSize, this.indexActual - 1).then(function (
        result
      ) {
        //Ha ido guay
        ctx.users = [];
        for (var elemento in result.respuesta.content) {
          var user = {};
          user.id = result.respuesta.content[elemento].id;
          user.email = result.respuesta.content[elemento].email;
          user.username = result.respuesta.content[elemento].username;
          user.password = result.respuesta.content[elemento].password;
          user.firstName = result.respuesta.content[elemento].firstName;
          user.lastName = result.respuesta.content[elemento].lastName;
          user.imageBase64 = result.respuesta.content[elemento].imageBase64;
          ctx.users.push(user);
          ctx.totalPages = result.respuesta.totalPages;
          ctx.totalPagesArray = [];
          for (var i = 1; i <= ctx.totalPages; i++) {
            ctx.totalPagesArray.push(i);
          }
          ctx.totalElements = result.respuesta.totalElements;
        }
      });
    },
  },
};
</script>
<style>
input[type='number'] {
  text-align: center !important;
}

</style>