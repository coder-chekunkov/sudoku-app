package com.cdr.app.utils

sealed class Exceptions : Exception()

class DbUnknownException : Exceptions()
class EmptyStatisticListException : Exception()
class StatisticGameInfoNotExist : Exceptions()