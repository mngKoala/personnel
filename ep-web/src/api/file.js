import request from '@/utils/request'

export function listPersonFile(query) {
  return request({
    url: '/person/file/list',
    method: 'get',
    params: query
  })
}

export function createPersonFile(data) {
  return request({
    url: '/person/file/create',
    method: 'post',
    data
  })
}

export function readminPersonFile(data) {
  return request({
    url: '/person/file/readmin',
    method: 'get',
    data
  })
}

export function updatePersonFile(data) {
  return request({
    url: '/person/file/update',
    method: 'post',
    data
  })
}

export function deletePersonFile(data) {
  return request({
    url: '/person/file/delete',
    method: 'post',
    data
  })
}
