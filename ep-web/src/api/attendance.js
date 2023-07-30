import request from '@/utils/request'

export function listAttendanceRec(query) {
  return request({
    url: '/attendance/rec/list',
    method: 'get',
    params: query
  })
}

export function createAttendanceRec(data) {
  return request({
    url: '/attendance/rec/create',
    method: 'post',
    data
  })
}

export function readminAttendanceRec(data) {
  return request({
    url: '/attendance/rec/readmin',
    method: 'get',
    data
  })
}

export function updateAttendanceRec(data) {
  return request({
    url: '/attendance/rec/update',
    method: 'post',
    data
  })
}

export function deleteAttendanceRec(data) {
  return request({
    url: '/attendance/rec/delete',
    method: 'post',
    data
  })
}
