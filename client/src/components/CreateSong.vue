<template>
  <v-layout>
    <v-flex xs4>
      <panel title="Song Metadata">
        <v-text-field
          data-test-id="newSongTitle"
          label="Title"
          required
          :rules="[required]"
          v-model="song.title"
          id="sngTitle"
        ></v-text-field>

        <v-text-field
          data-test-id="newSongArtist"
          label="Artist"
          required
          :rules="[required]"
          v-model="song.artist"
          id="sngArtist"
        ></v-text-field>

        <v-text-field
          data-test-id="newSongGenre"
          label="Genre"
          required
          :rules="[required]"
          v-model="song.genre"
          id="sngGenre"
        ></v-text-field>

        <v-text-field
          data-test-id="newSongAlbum"
          label="Album"
          required
          :rules="[required]"
          v-model="song.album"
          id="sngAlbum"
        ></v-text-field>

        <v-text-field
          data-test-id="newSongAlbumImageUrl"
          label="Album Image Url"
          required
          :rules="[required]"
          v-model="song.albumImageUrl"
          id="sngAlbumImg"
        ></v-text-field>

        <v-text-field
          data-test-id="newSongYouTubeId"
          label="YouTube ID"
          required
          :rules="[required]"
          v-model="song.youtubeId"
          id="sngYoutube"
        ></v-text-field>
      </panel>
    </v-flex>

    <v-flex xs8>
      <panel title="Song Structure" class="ml-2">
        <v-text-field
          data-test-id="newSongStructure"
          label="Tab"
          multi-line
          required
          :rules="[required]"
          v-model="song.tab"
          id="sngTab"
        ></v-text-field>

        <v-text-field
          label="Lyrics"
          data-test-id="newSongLyrics"
          multi-line
          required
          :rules="[required]"
          v-model="song.lyrics"
          id="sngLyrics"
        ></v-text-field>
      </panel>

      <div class="danger-alert" v-if="error">
        {{ error }}
      </div>

      <v-btn dark class="cyan" id="sngBtn" @click="create" data-test-id="createSong"> Create Song </v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import SongsService from '@/services/SongsService'

export default {
  data() {
    return {
      song: {
        title: null,
        artist: null,
        genre: null,
        album: null,
        albumImageUrl: null,
        youtubeId: null,
        lyrics: null,
        tab: null,
      },
      error: null,
      required: value => !!value || 'Required.',
    }
  },
  methods: {
    async create() {
      this.error = null
      const areAllFieldsFilledIn = Object.keys(this.song).every(key => !!this.song[key])
      if (!areAllFieldsFilledIn) {
        this.error = 'Please fill in all the required fields.'
        return
      }

      try {
        await SongsService.post(this.song)
        this.$router.push({
          name: 'songs',
        })
      } catch (err) {
        console.log(err)
      }
    },
  },
}
</script>

<style scoped></style>
