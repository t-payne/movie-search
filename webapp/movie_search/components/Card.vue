<template>
  <div style="max-width: 400px; margin: auto;">
    <v-container fluid style="min-height: 0" grid-list-lg>
      <v-layout row wrap>
        <v-flex xs12>
          <v-card color="cyan darken-2" class="white--text">
            <v-layout>
              <v-flex xs5>
                <v-img :src="image" height="200px" contain></v-img>
              </v-flex>
              <v-flex xs7>
                <v-card-title primary-title>
                  <div>
                    <div class="headline">{{title}}</div>
                    <div>{{popularity_summary}}</div>
                  </div>
                </v-card-title>
              </v-flex>
            </v-layout>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
export default {
  props: ["title", "image", "popularity_summary"]
};
</script>
