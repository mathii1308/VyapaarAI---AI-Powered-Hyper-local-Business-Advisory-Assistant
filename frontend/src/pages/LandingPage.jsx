import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import Hero from '../components/Hero';
import HealthCard from '../components/HealthCard';
import ProblemSection from '../components/ProblemSection';
import HowItWorks from '../components/HowItWorks';
import CapabilitiesSection from '../components/CapabilitiesSection';
import CtaSection from '../components/CtaSection';
import Footer from '../components/Footer';
import BusinessProfileForm from '../components/BusinessProfileForm';
import BusinessAnalysisView from '../components/BusinessAnalysisView';
import ChatPage from './ChatPage';
import { fetchHealthStatus, saveBusinessProfile } from '../services/api';

export default function LandingPage() {
  const [activeView, setActiveView] = useState('landing'); // 'landing' | 'profile' | 'analysis' | 'chat'
  const [healthStatus, setHealthStatus] = useState(null);
  const [loading, setLoading] = useState(true);
  const [selectedProfile, setSelectedProfile] = useState(null);
  const [activeProfileId, setActiveProfileId] = useState(null);

  const checkHealth = async () => {
    setLoading(true);
    const result = await fetchHealthStatus();
    setHealthStatus(result);
    setLoading(false);
  };

  useEffect(() => {
    checkHealth();

    // Support URL query param e.g. /chat?businessId=12
    const params = new URLSearchParams(window.location.search);
    const bId = params.get('businessId') || params.get('businessProfileId');
    if (bId) {
      setActiveProfileId(bId);
      setActiveView('chat');
    }
  }, []);

  const handleNavigate = (view, profileId = null) => {
    if (profileId) {
      setActiveProfileId(profileId);
    }
    setActiveView(view);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleFormSubmit = async (formData) => {
    // Send POST /api/business request to Spring Boot backend
    const response = await saveBusinessProfile(formData);
    
    if (response.success && response.data) {
      setSelectedProfile(response.data);
      setActiveProfileId(response.data.id);
      setActiveView('analysis');
      window.scrollTo({ top: 0, behavior: 'smooth' });
    } else {
      // Fallback calculation in case backend is offline, for seamless UX
      const revenue = formData.monthlyRevenue || 0;
      const expenses = formData.monthlyExpenses || 0;
      const netProfit = revenue - expenses;
      const profitMargin = revenue > 0 ? (netProfit / revenue) * 100 : 0;
      const monthlyDebt = formData.monthlyLoanPayment || 0;
      const dscr = monthlyDebt > 0 ? netProfit / monthlyDebt : null;

      const fallbackProfile = {
        ...formData,
        id: Math.floor(Math.random() * 900) + 100,
        netMonthlyProfit: netProfit,
        profitMarginPercent: Math.round(profitMargin * 100) / 100,
        debtServiceCoverageRatio: dscr ? Math.round(dscr * 100) / 100 : null,
        isDebtFree: monthlyDebt <= 0,
        debtBurdenPercent: revenue > 0 ? Math.round((monthlyDebt / revenue) * 10000) / 100 : 0,
        financialHealthScore: netProfit > 0 ? 80 : 30,
        financialHealthTier: netProfit > 0 ? 'Optimal Health' : 'Critical Deficit',
        maxSafeMonthlyEmi: Math.max(0, (netProfit * 0.4) - monthlyDebt),
        estimatedMaxLoanAmount: Math.round(Math.max(0, (netProfit * 0.4) - monthlyDebt) * 30),
        createdAt: new Date().toISOString()
      };

      setSelectedProfile(fallbackProfile);
      setActiveProfileId(fallbackProfile.id);
      setActiveView('analysis');
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  };

  return (
    <div className="app-container">
      <Navbar 
        activeView={activeView}
        onNavigate={handleNavigate}
        healthStatus={healthStatus} 
        loading={loading} 
      />

      <main>
        {activeView === 'landing' && (
          <>
            <Hero onGetStarted={() => handleNavigate('profile')} />
            <div className="section-container">
              <HealthCard 
                healthStatus={healthStatus} 
                loading={loading} 
                onRefresh={checkHealth} 
              />
            </div>
            <ProblemSection onGetStarted={() => handleNavigate('profile')} />
            <HowItWorks />
            <CapabilitiesSection />
            <CtaSection onGetStarted={() => handleNavigate('profile')} />
          </>
        )}

        {activeView === 'profile' && (
          <BusinessProfileForm 
            onSubmitSuccess={handleFormSubmit}
            onCancel={() => handleNavigate('landing')}
          />
        )}

        {activeView === 'analysis' && (
          <BusinessAnalysisView 
            profile={selectedProfile}
            onNavigate={handleNavigate}
            onNewAnalysis={() => handleNavigate('profile')}
          />
        )}

        {activeView === 'chat' && (
          <ChatPage 
            businessId={activeProfileId}
            onNavigate={handleNavigate}
          />
        )}
      </main>

      <Footer onNavigate={handleNavigate} />
    </div>
  );
}
