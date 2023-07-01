import http from "k6/http";
import { check } from "k6";
import { tagWithCurrentStageIndex } from 'https://jslib.k6.io/k6-utils/1.3.0/index.js';

export let options = {
  stages: [
    // Ramp-up from 1 to 30 VUs in 30s
    { duration: "30s", target: 1000 },

    // Stay on 30 VUs for 60s
    { duration: "60s", target: 500 },

    // Ramp-down from 30 to 0 VUs in 10s
    { duration: "10s", target: 0 }
  ],
};

export default function () {
  tagWithCurrentStageIndex();
  const graphQuery = `{
    findAllPosts {
      id,
      title,
      content,
      regDate,
      bbsComments {
        id
      }
    }
  }`

  let res = http.post("http://host.docker.internal:8080/graphql", JSON.stringify({ query: graphQuery }),
    { headers: { 'Content-Type': 'application/json' } });

  check(res, { "status is 200": (r) => r.status === 200 });
}
