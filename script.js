import http from 'k6/http';
import { sleep, check  } from 'k6';

export const options = {
  vus: 30,
  duration: '60s',
};

export default function () {
  const res = http.get('http://localhost:8080/threads');
  
  console.log(res.body);
  
  check(res, {
    'status is 200': (r) => r.status === 200
  });  
  
  sleep(1);
}