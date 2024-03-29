package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateResetVo
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory

interface CreditEvaluationCommandUsecase {
    fun evaluate(evaluateVo: CreditEvaluateVo): CreditEvaluationHistory
    fun reset(vo: CreditEvaluateResetVo): CreditEvaluationHistory
}
